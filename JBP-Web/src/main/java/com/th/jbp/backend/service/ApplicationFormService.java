package com.th.jbp.backend.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.ApplicationFormDriverM;
import com.th.jbp.jpa.entity.ApplicationFormEducationM;
import com.th.jbp.jpa.entity.ApplicationFormM;
import com.th.jbp.jpa.entity.ApplicationFormWorkExpM;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.repositories.ApplicationFormRepository;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;

@Service("applicationFormService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ApplicationFormService implements IApplicationFormService{
	
	@Autowired
	ApplicationFormRepository repository;
	
	@Autowired
	ClassifierValueRepository classifierValueRepository;
	
	@Autowired
	ApplicationFormEducationService applicationFormEducationService;
	
	@Autowired
	ApplicationFormWorkExpService applicationFormWorkExpService;
	
	@Autowired
	ApplicationFormDriverService applicationFormDriverService;
	
	@Autowired
	EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(ApplicationFormService.class);

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<ApplicationFormM> getByKey(ApplicationFormM entity) {
		ObjectResult<ApplicationFormM> result = new ObjectResult<>();
		ApplicationFormM appForm = repository.findOne(entity.getApplicationFormId());
		if (appForm == null || entity == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, appForm);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<List<ApplicationFormM>> lists() {
		ObjectResult<List<ApplicationFormM>> result = new ObjectResult<>();
		List<ApplicationFormM> items = repository.findAll();
		if (items == null) {
			result = new ObjectResult<>(ObjectResult.SUCCESS, new ArrayList<>(0));
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, items);
		}
		return result;
	}

	@Override
	public ObjectResult<ApplicationFormM> save(ApplicationFormM entity) {
		ObjectResult<ApplicationFormM> result = new ObjectResult<>();
		if (entity == null || entity.getApplicationFormId() != null) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null.");
		} else {
			List<ApplicationFormEducationM> educations = entity.getEducations();
			List<ApplicationFormWorkExpM> workExps = entity.getWorkExps();
			ApplicationFormDriverM driverForm = entity.getDriverForm();
			entity.setEducations(null);
			entity.setWorkExps(null);
			entity.setDriverForm(null);
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			entity = repository.saveAndFlush(entity);
			LOGGER.info("ApplicationFormId = "+entity.getApplicationFormId());
			
			if(educations != null && !educations.isEmpty()){
				applicationFormEducationService.insertAllByApplicationForm(educations, entity);
			}
			
			if(workExps != null && !workExps.isEmpty()){
				applicationFormWorkExpService.insertAllByApplicationForm(workExps, entity);
			}
			
			if(driverForm != null){
				applicationFormDriverService.insertByApplicationForm(driverForm, entity);
			}
			
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<ApplicationFormM> update(ApplicationFormM entity) {
		ObjectResult<ApplicationFormM> result = new ObjectResult<>();
		if (entity == null || entity.getApplicationFormId() == null || entity.getApplicationFormId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			List<ApplicationFormEducationM> educations = entity.getEducations();
			List<ApplicationFormWorkExpM> workExps = entity.getWorkExps();
			
			entity.getDriverForm().setApplicationFormId(entity.getApplicationFormId());
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			entity = repository.saveAndFlush(entity);
			
			List<ApplicationFormEducationM> oldEducations = applicationFormEducationService.getByApplicationFormId(entity.getApplicationFormId());
			if(oldEducations != null && !oldEducations.isEmpty()){
				applicationFormEducationService.deleteAllByApplicationFormId(entity.getApplicationFormId());
			}
			if(educations != null && !educations.isEmpty()){
				applicationFormEducationService.insertAllByApplicationForm(educations, entity);
			}
			
			List<ApplicationFormWorkExpM> oldWorkExps = applicationFormWorkExpService.getByApplicationFormId(entity.getApplicationFormId());
			if(oldWorkExps != null && !oldWorkExps.isEmpty()){
				applicationFormWorkExpService.deleteAllByApplicationFormId(entity.getApplicationFormId());
			}
			if(workExps != null && !workExps.isEmpty()){
				applicationFormWorkExpService.insertAllByApplicationForm(workExps, entity);
			}
			
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<ApplicationFormM> delete(ApplicationFormM entity) {
		ObjectResult<ApplicationFormM> result = new ObjectResult<>();
		if (entity == null || entity.getApplicationFormId() == null || entity.getApplicationFormId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ClassifierValueM status = classifierValueRepository.findOne(Status.DELETED.getId());
			entity.setStatus(status);
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			repository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ApplicationFormM> lists(String firstName, String lastName, PageRequest pageRequest) {
		LOGGER.debug("firstName = "+firstName+" , lastName = "+lastName+" , pageRequest = "+pageRequest);
		Page<ApplicationFormM> results = null;
		if(StringUtils.isNotEmpty(firstName) && StringUtils.isNotEmpty(lastName)){
			results = repository.findByFirstNameAndLastName("%"+firstName+"%", "%"+lastName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(firstName)){
			results = repository.findByFirstName("%"+firstName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(lastName)){
			results = repository.findByLastName("%"+lastName+"%", pageRequest);
		}else{
			results = repository.findAll(pageRequest);
		}
		
		return results;
	}

}
