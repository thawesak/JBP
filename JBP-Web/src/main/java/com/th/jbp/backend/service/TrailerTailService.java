package com.th.jbp.backend.service;

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
import com.th.jbp.jpa.entity.TrailerTailM;
import com.th.jbp.jpa.repositories.TrailerTailRepository;

@Service("trailerTailService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class TrailerTailService implements ITrailerTailService{
	
	@Autowired
	TrailerTailRepository repository;
	
	@Autowired
	EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(TrailerTailService.class);

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<TrailerTailM> getByKey(TrailerTailM entity) {
		ObjectResult<TrailerTailM> result = new ObjectResult<>();
		TrailerTailM resultEntity = repository.findOne(entity.getTrailerTailId());
		if (resultEntity == null || entity == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, resultEntity);
		}
		return result;
	}

	@Override
	public ObjectResult<List<TrailerTailM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<TrailerTailM> save(TrailerTailM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<TrailerTailM> update(TrailerTailM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<TrailerTailM> delete(TrailerTailM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TrailerTailM> lists(String licensePlate, PageRequest pageRequest) {
		LOGGER.debug("licensePlate = "+licensePlate+" , pageRequest = "+pageRequest);
		Page<TrailerTailM> results = null;
		if(StringUtils.isNotEmpty(licensePlate)){
			results = repository.findByLicensePlate("%"+licensePlate+"%", pageRequest);
		}else{
			results = repository.findAll(pageRequest);
		}
		
		return results;
	}

}
