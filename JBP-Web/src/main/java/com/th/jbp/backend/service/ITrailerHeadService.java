package com.th.jbp.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.jpa.entity.TrailerHeadM;

public interface ITrailerHeadService extends IService<TrailerHeadM>{

	public Page<TrailerHeadM> lists(String licensePlate, PageRequest pageRequest);
	
}
