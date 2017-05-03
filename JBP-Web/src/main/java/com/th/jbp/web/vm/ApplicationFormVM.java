package com.th.jbp.web.vm;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.backend.service.ApplicationFormService;
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.ApplicationFormDriverM;
import com.th.jbp.jpa.entity.ApplicationFormEducationM;
import com.th.jbp.jpa.entity.ApplicationFormM;
import com.th.jbp.jpa.entity.ApplicationFormWorkExpM;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.repositories.ApplicationFormDriverRepository;
import com.th.jbp.jpa.repositories.ApplicationFormEducationRepository;
import com.th.jbp.jpa.repositories.ApplicationFormRepository;
import com.th.jbp.jpa.repositories.ApplicationFormWorkExpRepository;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.web.utils.SecurityUtils;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ApplicationFormVM extends BaseVM{
	private static final long serialVersionUID = -5610206935497794590L;
	private static final Logger LOGGER = Logger.getLogger(ApplicationFormVM.class);
	
	@WireVariable
	ApplicationFormRepository applicationFormRepository;
	
	@WireVariable
	ApplicationFormEducationRepository applicationFormEducationRepository;
	
	@WireVariable
	ApplicationFormWorkExpRepository applicationFormWorkExpRepository;
	
	@WireVariable
	ApplicationFormDriverRepository applicationFormDriverRepository;
	
	@WireVariable
	ClassifierValueRepository classifierValueRepository;
	
	@WireVariable
	ApplicationFormService applicationFormService;
	
	private String firstName = null;
	private String lastName = null;
	
	private ListModelList<ApplicationFormM> records = null;
	private int pageSize = 10;
	private int activePage = 0;
	private int totalSize = 0;
	
	private ApplicationFormM applicationFormM;
	private Integer age;
	private List<ApplicationFormEducationM> educations = new ArrayList<ApplicationFormEducationM>();
	private List<ApplicationFormWorkExpM> workExps = new ArrayList<ApplicationFormWorkExpM>();
	private ApplicationFormDriverM driverForm = new ApplicationFormDriverM();
	private boolean displayEdit = true;
	
	@Init
	@NotifyChange({ "" })
	public void init() {
		LOGGER.debug("init ApplicationFormVM");
		this.records = new ListModelList<>();
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	private void loadItems() {
		LOGGER.info("loadItems--> activePage : " + this.activePage);
		PageRequest pageRequest = new PageRequest(activePage, pageSize);
		Page<ApplicationFormM> page = applicationFormService.lists(firstName, lastName, pageRequest);
		this.totalSize = Integer.parseInt(String.valueOf(page.getTotalElements()));
		this.records.clear();
		this.records.addAll(page.getContent());
	}
	
	private void clearValues(){
		this.activePage = 0;
		this.totalSize = 0;
		this.records = new ListModelList<>();
		this.age = null;
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "firstName", "lastName" })
	public void search() {
		LOGGER.info("excute method search");
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "firstName", "lastName" })
	public void clearSearch() {
		LOGGER.info("excute method clearSearch");
		setMode(MODE.SEARCH.name());
		firstName = null;
		lastName = null;
		clearValues();
	}
	
	@Command
	@NotifyChange({ "applicationFormM", "mode", "educations", "workExps", "driverForm" })
	public void newApplicationForm() {
		LOGGER.info("excute method addNewItem");
		setMode(MODE.ADD.name());
		this.applicationFormM = new ApplicationFormM();
		
		this.educations = new ArrayList<ApplicationFormEducationM>();
		this.educations.add(new ApplicationFormEducationM());
		
		this.workExps = new ArrayList<ApplicationFormWorkExpM>();
		this.workExps.add(new ApplicationFormWorkExpM());
		
		this.driverForm = new ApplicationFormDriverM();
	}
	
	@Command
	@NotifyChange({ "records", "applicationFormM", "mode", "activePage" })
	public void cancel() {
		LOGGER.info("excute method cancel");
		setMode(MODE.SEARCH.name());
		this.applicationFormM = null;
		this.activePage = 0;
	}
	
	@Command
	@NotifyChange({ "records", "applicationFormM", "mode", "educations", "workExps", "driverForm" })
	public void getByItem(@BindingParam("item") ApplicationFormM applicationFormM) {
		LOGGER.info("excute method getByItem");
		setMode(MODE.EDIT.name());
		this.applicationFormM = applicationFormService.getByKey(applicationFormM).getObject();
		
		this.educations = new ArrayList<ApplicationFormEducationM>();
		this.educations = applicationFormEducationRepository.findByApplicationFormId(this.applicationFormM.getApplicationFormId());
		this.educations.add(new ApplicationFormEducationM());
		
		this.workExps = new ArrayList<ApplicationFormWorkExpM>();
		this.workExps = applicationFormWorkExpRepository.findByApplicationFormId(this.applicationFormM.getApplicationFormId());
		this.workExps.add(new ApplicationFormWorkExpM());
		
		this.driverForm = new ApplicationFormDriverM();
		this.driverForm = initialDriverForm(applicationFormDriverRepository.findByApplicationFormId(this.applicationFormM.getApplicationFormId()));
	}
	
	@Command
	@NotifyChange({ "records", "applicationFormM" })
	public void delete(@BindingParam("item") final ApplicationFormM applicationFormM) {
		LOGGER.info("excute method delete");
		String message = getMessage(MESSAGE_CONFIRM_DELETE);
		execute(new MethodOperation() {
			@Override
			public void execute() {
				Clients.showBusy("Waiting for server...");
				applicationFormM.setUpdateBy(SecurityUtils.getWebUserDetails());
				try{
					ObjectResult<ApplicationFormM> objectResult = applicationFormService.delete(applicationFormM);
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
				ApplicationFormM model = getApplicationFormM();

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
					educations = removeRowNullEducation();
					model.setEducations(educations);
					
					workExps = removeRowNullWorkExp();
					model.setWorkExps(workExps);
					
					model.setDriverForm(driverForm);
					
					if (isModeAdd) {
						applicationFormService.save(model);
					} else {
						applicationFormService.update(model);
					}
					showInformation(getMessage("message.operation.success", operation));
					setApplicationFormM(new ApplicationFormM());
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
	@NotifyChange({ "applicationFormM", "age" })
	public void onAgeChange(){
		Calendar now = Calendar.getInstance();
		Calendar dob = Calendar.getInstance();
		dob.setTime(applicationFormM.getBirthday());
		if (dob.after(now)) {
		  throw new IllegalArgumentException("Can't be born in the future");
		}
		int year1 = now.get(Calendar.YEAR);
		int year2 = dob.get(Calendar.YEAR);
		this.age = year1 - year2;
		int month1 = now.get(Calendar.MONTH);
		int month2 = dob.get(Calendar.MONTH);
		if (month2 > month1) {
			this.age--;
		} else if (month1 == month2) {
		  int day1 = now.get(Calendar.DAY_OF_MONTH);
		  int day2 = dob.get(Calendar.DAY_OF_MONTH);
		  if (day2 > day1) {
			  this.age--;
		  }
		}

	}
	
//	@Command
//	@NotifyChange({ "driverForm" })
//	public void check_ans1_1(org.zkoss.zul.Checkbox chkbox) {
//		System.out.println("1111111111111");
//		if(chkbox.isChecked()){
//			driverForm.setQuestionNo1_1("Y");
//		}else{
//			driverForm.setQuestionNo1_1(null);
//		}
//	}
//	
//	@Command
//	@NotifyChange({ "driverForm" })
//	public void check_ans1_2() {
//		System.out.println("2222222222222");
//	}
//	
//	@Command
//	@NotifyChange({ "driverForm" })
//	public void check_ans1_3() {
//		System.out.println("3333333333333");
//	}
//	
//	@Command
//	@NotifyChange({ "driverForm" })
//	public void check_ans1_4(org.zkoss.zul.Checkbox chkbox) {
//		System.out.println("44444444444444");
//		if(chkbox.isChecked()){
//			driverForm.setQuestionNo1_4("Y");
//		}else{
//			driverForm.setQuestionNo1_4(null);
//		}
//	}
	
//	@Command
//	public void changeEditableStatus(@BindingParam("education") ApplicationFormEducationM education) {
//		education.setEditingStatus(!education.isEditingStatus());
//        refreshRowTemplate(education);
//    }
	
//	@Command
//    public void confirm(@BindingParam("education") ApplicationFormEducationM education) {
////		educationService.update(education);
//        changeEditableStatus(education);
//        refreshRowTemplate(education);
//    }
	
	@Command
	@NotifyChange({ "educations" })
	public void addLineEducation(){
		if(this.educations == null || this.educations.size() < 8){
			this.educations.add(new ApplicationFormEducationM());
		}else{
			 Messagebox.show(getMessage("applicationform.education.addline.error"), "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command
	@NotifyChange({ "workExps" })
	public void addLineWorkExp(){
		this.workExps.add(new ApplicationFormWorkExpM());
	}
	
	public void refreshRowTemplate(ApplicationFormEducationM education) {
        //replace the element in the collection by itself to trigger a model update
		educations.set(educations.indexOf(education), education);       
    }
	
	private String validate() {
		StringBuilder sb = new StringBuilder();
		final boolean isModeAdd = MODE.ADD.name().equals(getMode());
		final boolean isModeEdit = MODE.EDIT.name().equals(getMode());
		int i = 1;
		if(isModeAdd || isModeEdit){
			if(StringUtils.isEmpty(applicationFormM.getFirstName())){
				sb.append(i++ + "." + getMessage("applicationform.firstname.required"));
				sb.append(NEW_LINE);
			}
			if(StringUtils.isEmpty(applicationFormM.getLastName())){
				sb.append(i++ + "." + getMessage("applicationform.lastname.required"));
				sb.append(NEW_LINE);
			}
			if(StringUtils.isEmpty(applicationFormM.getPosition())){
				sb.append(i++ + "." + getMessage("applicationform.position.required"));
				sb.append(NEW_LINE);
			}
		}
		
		return sb.toString();
	}
	
	@GlobalCommand
	@NotifyChange({ "records", "applicationFormM", "activePage", "totalSize", "pageSize", "mode" })
	public void refreshView() {
		LOGGER.info("excute method refreshView");
	}
	
	@Command
	@NotifyChange({ "applicationFormM", "imageProfile" })
	public void getUploadedImageProfile(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	applicationFormM.setImageProfile(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "applicationFormM", "imageCard" })
	public void getUploadedImageCard(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	applicationFormM.setImageCard(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "driverForm", "imageDriverCard" })
	public void getUploadedImageDriverCard(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	driverForm.setImageDriverCard(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "driverForm", "imageHealthCheck1" })
	public void getUploadedImageHealthCheck1(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	driverForm.setImageHealthCheck1(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "driverForm", "imageHealthCheck2" })
	public void getUploadedImageHealthCheck2(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	driverForm.setImageHealthCheck2(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "driverForm", "imageHealthCheck3" })
	public void getUploadedImageHealthCheck3(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	driverForm.setImageHealthCheck3(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "applicationFormM" })
	public void viewImageProfile() {
		LOGGER.debug("excute method viewImageProfile");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", applicationFormM.getImageProfile());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "applicationFormM" })
	public void viewImageCard() {
		LOGGER.debug("excute method viewImageCard");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", applicationFormM.getImageCard());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "driverForm" })
	public void viewImageDriverCard() {
		LOGGER.debug("excute method viewImageDriverCard");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", driverForm.getImageDriverCard());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "driverForm" })
	public void viewImageHealthCheck1() {
		LOGGER.debug("excute method viewImageHealthCheck1");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", driverForm.getImageHealthCheck1());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "driverForm" })
	public void viewImageHealthCheck2() {
		LOGGER.debug("excute method viewImageHealthCheck2");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", driverForm.getImageHealthCheck2());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "driverForm" })
	public void viewImageHealthCheck3() {
		LOGGER.debug("excute method viewImageHealthCheck3");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", driverForm.getImageHealthCheck3());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	private List<ApplicationFormEducationM> removeRowNullEducation(){
		List<ApplicationFormEducationM> results = new ArrayList<>();
		for(ApplicationFormEducationM edu : this.educations){
			if(StringUtils.isNotEmpty(edu.getInstitution()) || StringUtils.isNotEmpty(edu.getLevel()) 
					|| StringUtils.isNotEmpty(edu.getMajor()) || StringUtils.isNotEmpty(edu.getStudyFrom())
					|| StringUtils.isNotEmpty(edu.getStudyTo())){
				results.add(edu);
			}
		}
		
		return results;
	}
	
	private List<ApplicationFormWorkExpM> removeRowNullWorkExp(){
		List<ApplicationFormWorkExpM> results = new ArrayList<>();
		for(ApplicationFormWorkExpM workExp : this.workExps){
			if(StringUtils.isNotEmpty(workExp.getCompanyName()) || StringUtils.isNotEmpty(workExp.getEndDate()) 
					|| StringUtils.isNotEmpty(workExp.getJobDescription()) || StringUtils.isNotEmpty(workExp.getPosition())
					|| StringUtils.isNotEmpty(workExp.getReasonResign()) || StringUtils.isNotEmpty(workExp.getStartDate())
					|| (workExp.getSalary() != null && workExp.getSalary().compareTo(BigDecimal.ZERO) > 0)
					|| StringUtils.isNotEmpty(workExp.getTel())){
				results.add(workExp);
			}
		}
		
		return results;
	}
	
	private ApplicationFormDriverM initialDriverForm(ApplicationFormDriverM model){
		return model == null ? new ApplicationFormDriverM() : model;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ListModelList<ApplicationFormM> getRecords() {
		return records;
	}

	public void setRecords(ListModelList<ApplicationFormM> records) {
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

	public ApplicationFormM getApplicationFormM() {
		return applicationFormM;
	}

	public void setApplicationFormM(ApplicationFormM applicationFormM) {
		this.applicationFormM = applicationFormM;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isDisplayEdit() {
		return displayEdit;
	}

	public void setDisplayEdit(boolean displayEdit) {
		this.displayEdit = displayEdit;
	}

	public List<ApplicationFormEducationM> getEducations() {
		return educations;
	}

	public void setEducations(List<ApplicationFormEducationM> educations) {
		this.educations = educations;
	}

	public List<ApplicationFormWorkExpM> getWorkExps() {
		return workExps;
	}

	public void setWorkExps(List<ApplicationFormWorkExpM> workExps) {
		this.workExps = workExps;
	}

	public ApplicationFormDriverM getDriverForm() {
		return driverForm;
	}

	public void setDriverForm(ApplicationFormDriverM driverForm) {
		this.driverForm = driverForm;
	}

}
