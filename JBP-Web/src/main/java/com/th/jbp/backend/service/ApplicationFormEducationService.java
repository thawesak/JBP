package com.th.jbp.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.ApplicationFormEducationM;
import com.th.jbp.jpa.entity.ApplicationFormM;
import com.th.jbp.jpa.repositories.ApplicationFormEducationRepository;

@Service("applicationFormEducationService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ApplicationFormEducationService implements IApplicationFormEducationService{

	@Autowired
	ApplicationFormEducationRepository repository;
	
	@Autowired
	EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(ApplicationFormEducationService.class);

	@Override
	public ObjectResult<ApplicationFormEducationM> getByKey(ApplicationFormEducationM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<List<ApplicationFormEducationM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormEducationM> save(ApplicationFormEducationM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormEducationM> update(ApplicationFormEducationM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormEducationM> delete(ApplicationFormEducationM entity) {
		ObjectResult<ApplicationFormEducationM> result = new ObjectResult<>();
		if (entity == null || entity.getApplicationFormEducationId() == null || entity.getApplicationFormEducationId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ApplicationFormEducationM formEducation = repository.findOne(entity.getApplicationFormEducationId());
			repository.delete(formEducation);
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ApplicationFormEducationM> getByApplicationFormId(Long applicationFormId) {
		List<ApplicationFormEducationM> results = new ArrayList<ApplicationFormEducationM>();
		results = repository.findByApplicationFormId(applicationFormId);
		
		return results;
	}

	@Override
	public ObjectResult<List<ApplicationFormEducationM>> deleteAllByApplicationFormId(Long applicationFormId) {
		ObjectResult<List<ApplicationFormEducationM>> result = new ObjectResult<>();
		if (applicationFormId == null || applicationFormId.equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			List<ApplicationFormEducationM> educations = getByApplicationFormId(applicationFormId);
			for(ApplicationFormEducationM education : educations){
				delete(education);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}

	@Override
	public ObjectResult<List<ApplicationFormEducationM>> insertAllByApplicationForm(List<ApplicationFormEducationM> educations, ApplicationFormM applicationForm) {
		ObjectResult<List<ApplicationFormEducationM>> result = new ObjectResult<>();
		if (applicationForm == null || applicationForm.getApplicationFormId() == null || applicationForm.getApplicationFormId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			for(ApplicationFormEducationM education : educations){
				education.setApplicationForm(applicationForm);
				repository.saveAndFlush(education);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}
	
	
}
