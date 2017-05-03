package com.th.jbp.backend.service;

import java.util.List;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.ApplicationFormEducationM;
import com.th.jbp.jpa.entity.ApplicationFormM;

public interface IApplicationFormEducationService extends IService<ApplicationFormEducationM>{

	public List<ApplicationFormEducationM> getByApplicationFormId(Long applicationFormId);
	
	public ObjectResult<List<ApplicationFormEducationM>> deleteAllByApplicationFormId(Long applicationFormId);
	
	public ObjectResult<List<ApplicationFormEducationM>> insertAllByApplicationForm(List<ApplicationFormEducationM> educations, ApplicationFormM applicationForm);
}
