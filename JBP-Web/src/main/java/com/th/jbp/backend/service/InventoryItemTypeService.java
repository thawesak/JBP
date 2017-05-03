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
import com.th.jbp.jpa.entity.InventoryItemTypeM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.InventoryItemTypeRepository;

@Service("inventoryItemTypeService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class InventoryItemTypeService implements IInventoryItemTypeService{
	private static final Logger LOGGER = Logger.getLogger(InventoryItemTypeService.class);
	
	@Autowired
	InventoryItemTypeRepository inventoryItemTypeRepository;
	
	@Autowired
	ClassifierValueRepository classifierValueRepository;
	
	@Autowired
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<InventoryItemTypeM> getByKey(InventoryItemTypeM entity) {
		ObjectResult<InventoryItemTypeM> result = new ObjectResult<>();
		InventoryItemTypeM inventoryItemTypeM = inventoryItemTypeRepository.findOne(entity.getInventoryItemTypeId());
		if (inventoryItemTypeM == null || entity == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, inventoryItemTypeM);
		}
		return result;
	}

	@Override
	public ObjectResult<List<InventoryItemTypeM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<InventoryItemTypeM> save(InventoryItemTypeM entity) {
		ObjectResult<InventoryItemTypeM> result = new ObjectResult<>();
		if (entity == null || entity.getInventoryItemTypeId() != null) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null.");
		} else {
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			inventoryItemTypeRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InventoryItemTypeM> update(InventoryItemTypeM entity) {
		ObjectResult<InventoryItemTypeM> result = new ObjectResult<>();
		if (entity == null || entity.getInventoryItemTypeId() == null || entity.getInventoryItemTypeId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			inventoryItemTypeRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InventoryItemTypeM> delete(InventoryItemTypeM entity) {
		ObjectResult<InventoryItemTypeM> result = new ObjectResult<>();
		if (entity == null || entity.getInventoryItemTypeId() == null || entity.getInventoryItemTypeId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ClassifierValueM status = classifierValueRepository.findOne(Status.DELETED.getId());
			entity.setStatus(status);
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			inventoryItemTypeRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<InventoryItemTypeM> lists(String name, String prefix, PageRequest pageRequest) {
		LOGGER.debug("name = "+name+" , prefix = "+prefix+" , pageRequest = "+pageRequest);
		Page<InventoryItemTypeM> results = null;
		if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(prefix)){
			results = inventoryItemTypeRepository.findByNameAndPrefix("%"+name+"%", "%"+prefix+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(name)){
			results = inventoryItemTypeRepository.findByName("%"+name+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(prefix)){
			results = inventoryItemTypeRepository.findByPrefix("%"+prefix+"%", pageRequest);
		}else{
			results = inventoryItemTypeRepository.findAll(pageRequest);
		}
		
		return results;
	}

	

}