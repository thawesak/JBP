package com.th.jbp.backend.service;

import java.sql.Timestamp;
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
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.entity.TrailerHeadM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.TrailerHeadRepository;

@Service("trailerHeadService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class TrailerHeadService implements ITrailerHeadService{
	
	@Autowired
	TrailerHeadRepository repository;
	
	@Autowired
	ClassifierValueRepository classifierValueRepository;
	
	@Autowired
	EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(TrailerHeadService.class);

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<TrailerHeadM> getByKey(TrailerHeadM entity) {
		ObjectResult<TrailerHeadM> result = new ObjectResult<>();
		TrailerHeadM resultEntity = repository.findOne(entity.getTrailerHeadId());
		if (resultEntity == null || entity == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, resultEntity);
		}
		return result;
	}

	@Override
	public ObjectResult<List<TrailerHeadM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<TrailerHeadM> save(TrailerHeadM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<TrailerHeadM> update(TrailerHeadM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<TrailerHeadM> delete(TrailerHeadM entity) {
		ObjectResult<TrailerHeadM> result = new ObjectResult<>();
		if (entity == null || entity.getTrailerHeadId() == null || entity.getTrailerHeadId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ClassifierValueM status = classifierValueRepository.findOne(Status.DELETED.getId());
			entity.setStatus(status);
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			repository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TrailerHeadM> lists(String licensePlate, PageRequest pageRequest) {
		LOGGER.debug("licensePlate = "+licensePlate+" , pageRequest = "+pageRequest);
		Page<TrailerHeadM> results = null;
		if(StringUtils.isNotEmpty(licensePlate)){
			results = repository.findByLicensePlate("%"+licensePlate+"%", pageRequest);
		}else{
			results = repository.findAll(pageRequest);
		}
		
		return results;
	}

}
