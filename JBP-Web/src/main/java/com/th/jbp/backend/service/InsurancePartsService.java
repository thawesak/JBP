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
import com.th.jbp.jpa.entity.InsurancePartsItemM;
import com.th.jbp.jpa.entity.InsurancePartsM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.InsurancePartsRepository;

@Service("insurancePartsService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class InsurancePartsService implements IInsurancePartsService{
	private static final Logger LOGGER = Logger.getLogger(InsurancePartsService.class);

	@Autowired
	ClassifierValueRepository classifierValueRepository;
	
	@Autowired
	InsurancePartsRepository insurancePartsRepository;
	
	@Autowired
	EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public ObjectResult<InsurancePartsM> getByKey(InsurancePartsM entity) {
		ObjectResult<InsurancePartsM> result = new ObjectResult<>();
		InsurancePartsM insurancePartsM = insurancePartsRepository.findOne(entity.getInsurancePartsId());
		if (insurancePartsM == null || entity == null) {
//			if(insurancePartsM.getPartsItems() != null){
//				insurancePartsM.setPartsItems(insurancePartsM.getPartsItems());
//			}
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, insurancePartsM);
		}
		return result;
	}

	@Override
	public ObjectResult<List<InsurancePartsM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<InsurancePartsM> save(InsurancePartsM entity) {
		ObjectResult<InsurancePartsM> result = new ObjectResult<>();
		if (entity == null || entity.getInsurancePartsId() != null) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null.");
		} else {
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			List<InsurancePartsItemM> partsItems = entity.getPartsItems();
			entity = insurancePartsRepository.saveAndFlush(entity);
			for(InsurancePartsItemM insurancePartsItemM : partsItems){
				insurancePartsItemM.setInsuranceParts(entity);
				em.persist(insurancePartsItemM);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InsurancePartsM> update(InsurancePartsM entity) {
		ObjectResult<InsurancePartsM> result = new ObjectResult<>();
		if (entity == null || entity.getInsurancePartsId() == null || entity.getInsurancePartsId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			List<InsurancePartsItemM> partsItems = entity.getPartsItems();
			entity = insurancePartsRepository.saveAndFlush(entity);
			
			for(InsurancePartsItemM insurancePartsItemM : entity.getPartsItems()){
				em.remove(insurancePartsItemM);
			}
			
			for(InsurancePartsItemM insurancePartsItemM : partsItems){
				insurancePartsItemM.setInsuranceParts(entity);
				em.persist(insurancePartsItemM);
			}
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InsurancePartsM> delete(InsurancePartsM entity) {
		ObjectResult<InsurancePartsM> result = new ObjectResult<>();
		if (entity == null || entity.getInsurancePartsId() == null || entity.getInsurancePartsId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ClassifierValueM status = classifierValueRepository.findOne(Status.DELETED.getId());
			entity.setStatus(status);
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			insurancePartsRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<InsurancePartsM> lists(String companyName, String referenceCode, PageRequest pageRequest) {
		LOGGER.debug("companyName = "+companyName+" , referenceCode = "+referenceCode+" , pageRequest = "+pageRequest);
		Page<InsurancePartsM> results = null;
		if(StringUtils.isNotEmpty(companyName) && StringUtils.isNotEmpty(referenceCode)){
			results = insurancePartsRepository.findByCompanyNameAndReferenceCode("%"+companyName+"%", "%"+referenceCode+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(companyName) && StringUtils.isEmpty(referenceCode)){
			results = insurancePartsRepository.findByCompanyName("%"+companyName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(referenceCode) && StringUtils.isEmpty(companyName)){
			results = insurancePartsRepository.findByReferenceCode("%"+referenceCode+"%", pageRequest);
		}else{
			results = insurancePartsRepository.findAll(pageRequest);
		}
		
		return results;
	}

	@Override
	public ObjectResult<List<InsurancePartsItemM>> findPartsItemByInsurancePartsId(Long id) {
		ObjectResult<List<InsurancePartsItemM>> result = new ObjectResult<>();
		InsurancePartsM insurancePartsM = insurancePartsRepository.findOne(id);
		if (insurancePartsM == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			List<InsurancePartsItemM> partsItems = insurancePartsM.getPartsItems();
			result = new ObjectResult<>(ObjectResult.SUCCESS, partsItems);
		}
		return result;
	}

}
