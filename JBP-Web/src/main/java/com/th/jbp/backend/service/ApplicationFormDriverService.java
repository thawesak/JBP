package com.th.jbp.backend.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.ApplicationFormDriverM;
import com.th.jbp.jpa.entity.ApplicationFormM;
import com.th.jbp.jpa.repositories.ApplicationFormDriverRepository;

@Service("applicationFormDriverService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ApplicationFormDriverService implements IApplicationFormDriverService{
	
	@Autowired
	ApplicationFormDriverRepository repository;
	
	@Autowired
	EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(ApplicationFormDriverService.class);

	@Override
	public ObjectResult<ApplicationFormDriverM> getByKey(ApplicationFormDriverM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<List<ApplicationFormDriverM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormDriverM> save(ApplicationFormDriverM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormDriverM> update(ApplicationFormDriverM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormDriverM> delete(ApplicationFormDriverM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public ApplicationFormDriverM getByApplicationFormId(Long applicationFormId) {
		ApplicationFormDriverM result = new ApplicationFormDriverM();
		result = repository.findByApplicationFormId(applicationFormId);
		
		return result;
	}

	@Override
	public ObjectResult<ApplicationFormDriverM> deleteByApplicationFormId(Long applicationFormId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<ApplicationFormDriverM> insertByApplicationForm(ApplicationFormDriverM driver,
			ApplicationFormM applicationForm) {
		ObjectResult<ApplicationFormDriverM> result = new ObjectResult<>();
		if (applicationForm == null || applicationForm.getApplicationFormId() == null || applicationForm.getApplicationFormId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			if(driver != null){
				driver.setApplicationFormId(applicationForm.getApplicationFormId());
				driver.setApplicationForm(applicationForm);
				repository.saveAndFlush(driver);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}

	@Override
	public ObjectResult<ApplicationFormDriverM> updateByApplicationForm(ApplicationFormDriverM driver,
			ApplicationFormM applicationForm) {
		ObjectResult<ApplicationFormDriverM> result = new ObjectResult<>();
		if (applicationForm == null || applicationForm.getApplicationFormId() == null || applicationForm.getApplicationFormId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			if(driver != null){
				ApplicationFormDriverM oldDriver = repository.findByApplicationFormId(applicationForm.getApplicationFormId());
				driver.setApplicationFormId(oldDriver.getApplicationFormId());
				driver.setApplicationForm(applicationForm);
				repository.saveAndFlush(driver);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS);
		}
		return result;
	}

}
