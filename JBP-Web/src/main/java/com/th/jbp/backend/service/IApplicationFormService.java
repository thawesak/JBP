package com.th.jbp.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.jpa.entity.ApplicationFormM;

public interface IApplicationFormService extends IService<ApplicationFormM>{

	public Page<ApplicationFormM> lists(String firstName, String lastName, PageRequest pageRequest);
	
}
