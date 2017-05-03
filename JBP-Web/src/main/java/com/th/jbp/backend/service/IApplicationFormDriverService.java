package com.th.jbp.backend.service;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.ApplicationFormDriverM;
import com.th.jbp.jpa.entity.ApplicationFormM;

public interface IApplicationFormDriverService extends IService<ApplicationFormDriverM>{

	public ApplicationFormDriverM getByApplicationFormId(Long applicationFormId);
	
	public ObjectResult<ApplicationFormDriverM> deleteByApplicationFormId(Long applicationFormId);
	
	public ObjectResult<ApplicationFormDriverM> insertByApplicationForm(ApplicationFormDriverM driver, ApplicationFormM applicationForm);
	
	public ObjectResult<ApplicationFormDriverM> updateByApplicationForm(ApplicationFormDriverM driver, ApplicationFormM applicationForm);
	
}
