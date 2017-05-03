package com.th.jbp.web.vm;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.backend.service.InsuranceTrailerService;
import com.th.jbp.backend.service.UserService;
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.entity.InsuranceTrailerM;
import com.th.jbp.jpa.entity.UserM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.InsuranceTrailerRepository;
import com.th.jbp.web.utils.SecurityUtils;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class InsuranceTrailerVM extends BaseVM{
	private static final long serialVersionUID = -7252902717171841193L;
	private static final Logger LOGGER = Logger.getLogger(InsuranceTrailerVM.class);
	
	@WireVariable
	InsuranceTrailerService insuranceTrailerService;
	
	@WireVariable
	InsuranceTrailerRepository insuranceTrailerRepository;
	
	@WireVariable
	ClassifierValueRepository classifierValueRepository;
	
	@WireVariable
	UserService userService;
	
	private String companyName;
	private String referenceCode;
	private ClassifierValueM insuranceType;
	private ClassifierValueM insuranceType2;
	
	private ListModelList<InsuranceTrailerM> records = null;
	private List<ClassifierValueM> insuranceTypes = null;
	private InsuranceTrailerM insuranceTrailerM;
	private int pageSize = 10;
	private int activePage = 0;
	private int totalSize = 0;
	
	public InsuranceTrailerVM(){
		
	}
	
	@Init
	public void init() {
		this.records = new ListModelList<>();
		this.insuranceTypes = new ListModelList<>(classifierValueRepository.findAllInsuranceType());
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "companyName", "referenceCode", "insuranceType" })
	public void search() {
		LOGGER.info("excute method search");
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "companyName", "referenceCode", "insuranceType", "insuranceTrailerM" })
	public void clearSearch() {
		LOGGER.info("excute method clearSearch");
		setMode(MODE.SEARCH.name());
		setInsuranceTrailerM(new InsuranceTrailerM());
		companyName = null;
		referenceCode = null;
		insuranceType = null;
		clearValues();
	}
	
	@Command
	@NotifyChange({ "records", "insuranceTrailerM", "mode", "insuranceType2" })
	public void getByItem(@BindingParam("item") InsuranceTrailerM insuranceTrailerM) {
		LOGGER.info("excute method getByItem");
		setMode(MODE.EDIT.name());
		this.insuranceTrailerM = insuranceTrailerService.getByKey(insuranceTrailerM).getObject();
		ClassifierValueM resultType = classifierValueRepository.findOne(insuranceTrailerM.getInsuranceType().getClassifierValueId());
		this.insuranceType2 = resultType;
		LOGGER.info("insuranceType2 : " + this.insuranceType2);
	}
	
	@Command
	@NotifyChange({ "records", "insuranceTrailerM" })
	public void delete(@BindingParam("item") final InsuranceTrailerM entity) {
		LOGGER.info("excute method delete");
		String message = getMessage(MESSAGE_CONFIRM_DELETE);
		execute(new MethodOperation() {
			@Override
			public void execute() {
				Clients.showBusy("Waiting for server...");
				entity.setUpdateBy(SecurityUtils.getWebUserDetails());
				try{
					ObjectResult<InsuranceTrailerM> objectResult = insuranceTrailerService.delete(entity);
					if (objectResult.getCode() == ObjectResult.SUCCESS) {
						LOGGER.info("delete success load items again.");
						loadItems();
					}
				}catch(Exception e){
					LOGGER.error(e.toString(), e);
					showError("Error", getMessage("message.error.operation.fail", "Delete", e.getMessage()));
				}finally{
					Clients.clearBusy();
				}
				BindUtils.postGlobalCommand(null, null, "refreshView", null);
			}
		}, LOGGER, message);
	}
	
	@Command
	@NotifyChange({ "insuranceTrailerM", "mode", "insuranceType2" })
	public void addNewItem() {
		LOGGER.info("excute method addNewItem");
		setMode(MODE.ADD.name());
		this.insuranceTrailerM = new InsuranceTrailerM();
		this.insuranceType2 = null;
	}
	
	@Command
	public void save() {
		LOGGER.info("excute method save");
		LOGGER.info("MODE : " + getMode());
		String sb = StringUtils.trimToEmpty(validate());
		if (StringUtils.isNotEmpty(sb)) {
			Clients.clearBusy();
			showValidate(sb);
			return;
		}
		final boolean isModeAdd = MODE.ADD.name().equals(getMode());
		String message = getMessage(MODE.ADD.name().equals(getMode()) ? MESSAGE_CONFIRM_SAVE : MESSAGE_CONFIRM_UPDATE);
		final String operation = isModeAdd ? getMessage("save") : getMessage("update");
		execute(new MethodOperation() {
			@Override
			public void execute() {

				Clients.showBusy("Waiting for server...");
				InsuranceTrailerM model = getInsuranceTrailerM();
				LOGGER.info(model);

				Date date = new Date();
				if (isModeAdd) {
					ClassifierValueM status = classifierValueRepository.findOne(Status.ACTIVE.getId());
					model.setStatus(status);
				}
				
				if(isModeAdd){
					model.setCreateBy(SecurityUtils.getWebUserDetails());
					model.setCreateDate(new Timestamp(date.getTime()));
				}else{
					model.setUpdateBy(SecurityUtils.getWebUserDetails());
					model.setUpdateDate(new Timestamp(date.getTime()));
				}
				model.setInsuranceType(insuranceType2);
				try {
					LOGGER.info(model);
					if (isModeAdd) {
						insuranceTrailerService.save(model);
					} else {
						insuranceTrailerService.update(model);
					}
					showInformation(getMessage("message.operation.success", operation));
					setInsuranceTrailerM(new InsuranceTrailerM());
					setMode(MODE.SEARCH.name());
					setActivePage(0);
					loadItems();
				} catch (Exception e) {
					LOGGER.error(e.toString(), e);
					showError("Error", getMessage("message.error.operation.fail", operation, e.getMessage()));
				} finally {
					Clients.clearBusy();
				}
				BindUtils.postGlobalCommand(null, null, "refreshView", null);
			}
		}, LOGGER, message);

	}
	
	@Command
	@NotifyChange({ "records", "insuranceTrailerM", "mode", "activePage", "insuranceType2" })
	public void cancel() {
		LOGGER.info("excute method cancel");
		setMode(MODE.SEARCH.name());
		this.insuranceTrailerM = null;
		this.activePage = 0;
		this.insuranceType2 = null;
	}
	
	@GlobalCommand
	@NotifyChange({ "records", "insuranceTrailerM", "activePage", "totalSize", "pageSize", "mode", "companyName", "referenceCode", "insuranceType" })
	public void refreshView() {
		LOGGER.info("excute method refreshView");
	}
	
	private static Map<Long, UserM> CACHE_USERS = new HashMap<>();
	
	@SuppressWarnings("rawtypes")
	public Converter getMemberConverter() {
		return new Converter() {
			public Object coerceToUi(Object user, Component component, BindContext ctx) {
				if (user == null) {
					return "N/A";
				}
				Long userId = ((UserM) user).getUserId();
				UserM userM = CACHE_USERS.get(userId);
				if (userM == null) {
					userM = new UserM();
					userM.setUserId(userId);
					ObjectResult<UserM> rs = userService.getByKey(userM);
					if (rs.getCode() == ObjectResult.SUCCESS) {
						userM = rs.getObject();
						if (userM == null) {
							return "N/A";
						}
						CACHE_USERS.put(userId, userM);
					}
				}
				return String.format("%s %s", userM.getFirstName(), userM.getLastName());
			}

			public Object coerceToBean(Object val, Component component, BindContext ctx) {
				return null;
			}
		};
	}
	
	private String validate() {
		StringBuilder sb = new StringBuilder();
		final boolean isModeAdd = MODE.ADD.name().equals(getMode());
		int i = 1;
		if (StringUtils.isEmpty(this.insuranceTrailerM.getCompanyName())) {
			sb.append(i++ + "." + getMessage("insurance.2.company.name.required"));
			sb.append(NEW_LINE);
		}
		if (StringUtils.isEmpty(this.insuranceTrailerM.getDescription())) {
			sb.append(i++ + "." + getMessage("insurance.2.reference.code.required"));
			sb.append(NEW_LINE);
		}
		if (StringUtils.isEmpty(this.insuranceTrailerM.getReferenceCode())) {
			sb.append(i++ + "." + getMessage("insurance.2.description.required"));
			sb.append(NEW_LINE);
		}else{
			InsuranceTrailerM obj = insuranceTrailerRepository.findOneByReferenceCode(this.insuranceTrailerM.getReferenceCode());
			if(isModeAdd){
				if(obj != null){
					sb.append(i++ + "." + getMessage("insurance.2.reference.code.duplicate"));
					sb.append(NEW_LINE);
				}
			}else{
				if(obj != null && !obj.getInsuranceTrailerId().equals(this.insuranceTrailerM.getInsuranceTrailerId()) 
						&& obj.getReferenceCode().equals(this.insuranceTrailerM.getReferenceCode())){
					sb.append(i++ + "." + getMessage("insurance.2.reference.code.duplicate"));
					sb.append(NEW_LINE);
				}
			}
		}
		
		return sb.toString();
	}
	
	private void loadItems() {
		LOGGER.info("loadItems--> activePage : " + this.activePage);
		PageRequest pageRequest = new PageRequest(activePage, pageSize);
		Page<InsuranceTrailerM> page = insuranceTrailerService.lists(companyName, referenceCode, getInsuranceTypeId(insuranceType), pageRequest);
		this.totalSize = Integer.parseInt(String.valueOf(page.getTotalElements()));
		this.records.clear();
		this.records.addAll(page.getContent());
	}
	
	private void clearValues(){
		this.activePage = 0;
		this.totalSize = 0;
		this.records = new ListModelList<InsuranceTrailerM>();
	}
	
	private Long getInsuranceTypeId(ClassifierValueM classifierValueM){
		return classifierValueM != null ? classifierValueM.getClassifierValueId() : null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public ClassifierValueM getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(ClassifierValueM insuranceType) {
		this.insuranceType = insuranceType;
	}

	public ListModelList<InsuranceTrailerM> getRecords() {
		return records;
	}

	public void setRecords(ListModelList<InsuranceTrailerM> records) {
		this.records = records;
	}

	public List<ClassifierValueM> getInsuranceTypes() {
		return insuranceTypes;
	}

	public void setInsuranceTypes(List<ClassifierValueM> insuranceTypes) {
		this.insuranceTypes = insuranceTypes;
	}

	public InsuranceTrailerM getInsuranceTrailerM() {
		return insuranceTrailerM;
	}

	public void setInsuranceTrailerM(InsuranceTrailerM insuranceTrailerM) {
		this.insuranceTrailerM = insuranceTrailerM;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getActivePage() {
		return activePage;
	}

	@NotifyChange("records")
	public void setActivePage(int activePage) {
		this.activePage = activePage;
		this.totalSize = 0;
		this.records = new ListModelList<>();
		loadItems();
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public ClassifierValueM getInsuranceType2() {
		return insuranceType2;
	}

	public void setInsuranceType2(ClassifierValueM insuranceType2) {
		this.insuranceType2 = insuranceType2;
	}

}
