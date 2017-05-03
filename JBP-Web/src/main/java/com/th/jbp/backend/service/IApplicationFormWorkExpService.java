package com.th.jbp.backend.service;

import java.util.List;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.ApplicationFormM;
import com.th.jbp.jpa.entity.ApplicationFormWorkExpM;

public interface IApplicationFormWorkExpService extends IService<ApplicationFormWorkExpM>{
	
	public List<ApplicationFormWorkExpM> getByApplicationFormId(Long applicationFormId);
	
	public ObjectResult<List<ApplicationFormWorkExpM>> deleteAllByApplicationFormId(Long applicationFormId);
	
	public ObjectResult<List<ApplicationFormWorkExpM>> insertAllByApplicationForm(List<ApplicationFormWorkExpM> models, ApplicationFormM applicationForm);

}
