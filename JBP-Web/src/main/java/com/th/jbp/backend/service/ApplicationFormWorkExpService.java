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
import com.th.jbp.jpa.entity.ApplicationFormM;
import com.th.jbp.jpa.entity.ApplicationFormWorkExpM;
import com.th.jbp.jpa.repositories.ApplicationFormWorkExpRepository;

@Service("applicationFormWorkExpService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ApplicationFormWorkExpService implements IApplicationFormWorkExpService{
	
	@Autowired
	ApplicationFormWorkExpRepository repository;
	
	@Autowired
	EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(ApplicationFormWorkExpService.class);

	@Override
	public ObjectResult<ApplicationFormWorkExpM> getByKey(ApplicationFormWorkExpM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<List<ApplicationFormWorkExpM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormWorkExpM> save(ApplicationFormWorkExpM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormWorkExpM> update(ApplicationFormWorkExpM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormWorkExpM> delete(ApplicationFormWorkExpM entity) {
		ObjectResult<ApplicationFormWorkExpM> result = new ObjectResult<>();
		if (entity == null || entity.getApplicationFormWorkExpId() == null || entity.getApplicationFormWorkExpId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ApplicationFormWorkExpM model = repository.findOne(entity.getApplicationFormWorkExpId());
			repository.delete(model);
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ApplicationFormWorkExpM> getByApplicationFormId(Long applicationFormId) {
		List<ApplicationFormWorkExpM> results = new ArrayList<ApplicationFormWorkExpM>();
		results = repository.findByApplicationFormId(applicationFormId);
		
		return results;
	}

	@Override
	public ObjectResult<List<ApplicationFormWorkExpM>> deleteAllByApplicationFormId(Long applicationFormId) {
		ObjectResult<List<ApplicationFormWorkExpM>> result = new ObjectResult<>();
		if (applicationFormId == null || applicationFormId.equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			List<ApplicationFormWorkExpM> models = getByApplicationFormId(applicationFormId);
			for(ApplicationFormWorkExpM model : models){
				delete(model);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}

	@Override
	public ObjectResult<List<ApplicationFormWorkExpM>> insertAllByApplicationForm(
			List<ApplicationFormWorkExpM> models, ApplicationFormM applicationForm) {
		ObjectResult<List<ApplicationFormWorkExpM>> result = new ObjectResult<>();
		if (applicationForm == null || applicationForm.getApplicationFormId() == null || applicationForm.getApplicationFormId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			for(ApplicationFormWorkExpM model : models){
				model.setApplicationForm(applicationForm);
				repository.saveAndFlush(model);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}

}
