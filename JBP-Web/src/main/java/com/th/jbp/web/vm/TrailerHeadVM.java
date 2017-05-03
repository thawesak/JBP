package com.th.jbp.web.vm;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.backend.service.TrailerHeadService;
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.entity.TrailerHeadM;
import com.th.jbp.jpa.entity.TrailerTailM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.TrailerHeadRepository;
import com.th.jbp.jpa.repositories.TrailerTailRepository;
import com.th.jbp.web.utils.SecurityUtils;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TrailerHeadVM extends BaseVM{
	private static final long serialVersionUID = 8793338106612827938L;
	private static final Logger LOGGER = Logger.getLogger(TrailerHeadVM.class);
	
	@WireVariable
	TrailerHeadRepository trailerHeadRepository;
	
	@WireVariable
	TrailerHeadService trailerHeadService;
	
	@WireVariable
	TrailerTailRepository trailerTailRepository;
	
	@WireVariable
	ClassifierValueRepository classifierValueRepository;
	
	private String licensePlate;
	
	private ListModelList<TrailerHeadM> records = null;
	private ListModelList<TrailerTailM> trailerTails = null;
	private int pageSize = 10;
	private int activePage = 0;
	private int totalSize = 0;
	
	private TrailerHeadM trailerHeadM;

	public TrailerHeadVM(){
		
	}
	
	@Init
	@NotifyChange({ "" })
	public void init() {
		LOGGER.debug("init TrailerHeadVM");
		this.records = new ListModelList<>();
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	private void loadItems() {
		LOGGER.info("loadItems--> activePage : " + this.activePage);
		PageRequest pageRequest = new PageRequest(activePage, pageSize);
		Page<TrailerHeadM> page = trailerHeadService.lists(licensePlate, pageRequest);
		this.totalSize = Integer.parseInt(String.valueOf(page.getTotalElements()));
		this.records.clear();
		this.records.addAll(page.getContent());
	}
	
	private void clearValues(){
		this.activePage = 0;
		this.totalSize = 0;
		this.records = new ListModelList<>();
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "licensePlate" })
	public void search() {
		LOGGER.info("excute method search");
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "licensePlate" })
	public void clearSearch() {
		LOGGER.info("excute method clearSearch");
		setMode(MODE.SEARCH.name());
		licensePlate = null;
		clearValues();
	}
	
	@Command
	@NotifyChange({ "trailerHeadM", "mode" })
	public void newTrailerHead() {
		LOGGER.info("excute method addNewItem");
		setMode(MODE.ADD.name());
		this.trailerHeadM = new TrailerHeadM();
		loadTrailerTails();
	}
	
	@Command
	@NotifyChange({ "records", "trailerHeadM", "mode", "activePage" })
	public void cancel() {
		LOGGER.info("excute method cancel");
		setMode(MODE.SEARCH.name());
		this.trailerHeadM = null;
		this.activePage = 0;
	}
	
	@Command
	@NotifyChange({ "records", "trailerHeadM", "mode" })
	public void getByItem(@BindingParam("item") TrailerHeadM trailerHeadM) {
		LOGGER.info("excute method getByItem");
		setMode(MODE.EDIT.name());
		this.trailerHeadM = trailerHeadService.getByKey(trailerHeadM).getObject();
		loadTrailerTails();
	}
	
	@Command
	@NotifyChange({ "records", "trailerHeadM" })
	public void delete(@BindingParam("item") final TrailerHeadM trailerHeadM) {
		LOGGER.info("excute method delete");
		String message = getMessage(MESSAGE_CONFIRM_DELETE);
		execute(new MethodOperation() {
			@Override
			public void execute() {
				Clients.showBusy("Waiting for server...");
				trailerHeadM.setUpdateBy(SecurityUtils.getWebUserDetails());
				try{
					ObjectResult<TrailerHeadM> objectResult = trailerHeadService.delete(trailerHeadM);
					if (objectResult.getCode() == ObjectResult.SUCCESS) {
						LOGGER.info("delete sussess load items again.");
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
				TrailerHeadM model = getTrailerHeadM();

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
				try {
//					educations = removeRowNullEducation();
//					model.setEducations(educations);
//					
//					workExps = removeRowNullWorkExp();
//					model.setWorkExps(workExps);
//					
//					model.setDriverForm(driverForm);
					
					if (isModeAdd) {
						trailerHeadService.save(model);
					} else {
						trailerHeadService.update(model);
					}
					showInformation(getMessage("message.operation.success", operation));
					setTrailerHeadM(new TrailerHeadM());
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
	
	@GlobalCommand
	@NotifyChange({ "records", "trailerHeadM", "activePage", "totalSize", "pageSize", "mode" })
	public void refreshView() {
		LOGGER.info("excute method refreshView");
	}
	
	private String validate() {
		StringBuilder sb = new StringBuilder();
		final boolean isModeAdd = MODE.ADD.name().equals(getMode());
		int i = 1;
		
		return sb.toString();
	}
	
	private void loadTrailerTails(){
		this.trailerTails = new ListModelList<>(trailerTailRepository.findAllActive());
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public ListModelList<TrailerHeadM> getRecords() {
		return records;
	}

	public void setRecords(ListModelList<TrailerHeadM> records) {
		this.records = records;
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

	public TrailerHeadM getTrailerHeadM() {
		return trailerHeadM;
	}

	public void setTrailerHeadM(TrailerHeadM trailerHeadM) {
		this.trailerHeadM = trailerHeadM;
	}

	public ListModelList<TrailerTailM> getTrailerTails() {
		return trailerTails;
	}

	public void setTrailerTails(ListModelList<TrailerTailM> trailerTails) {
		this.trailerTails = trailerTails;
	}
}
