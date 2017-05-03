package com.th.jbp.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.jpa.entity.TrailerTailM;

public interface ITrailerTailService extends IService<TrailerTailM>{

	public Page<TrailerTailM> lists(String licensePlate, PageRequest pageRequest);
	
}
