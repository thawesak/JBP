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
import com.th.jbp.jpa.entity.InsuranceTrailerM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.InsuranceTrailerRepository;

@Service("insuranceTrailerService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class InsuranceTrailerService implements IInsuranceTrailerService{
	private static final Logger LOGGER = Logger.getLogger(InsuranceTrailerService.class);
	
	@Autowired
	ClassifierValueRepository classifierValueRepository;
	
	@Autowired
	InsuranceTrailerRepository insuranceTrailerRepository;
	
	@Autowired
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<InsuranceTrailerM> getByKey(InsuranceTrailerM entity) {
		ObjectResult<InsuranceTrailerM> result = new ObjectResult<>();
		InsuranceTrailerM insuranceTrailerM = insuranceTrailerRepository.findOne(entity.getInsuranceTrailerId());
		if (insuranceTrailerM == null || entity == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {	
			result = new ObjectResult<>(ObjectResult.SUCCESS, insuranceTrailerM);
		}
		return result;
	}

	@Override
	public ObjectResult<InsuranceTrailerM> save(InsuranceTrailerM entity) {
		ObjectResult<InsuranceTrailerM> result = new ObjectResult<>();
		if (entity == null || entity.getInsuranceTrailerId() != null) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null.");
		} else {
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			insuranceTrailerRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InsuranceTrailerM> update(InsuranceTrailerM entity) {
		ObjectResult<InsuranceTrailerM> result = new ObjectResult<>();
		if (entity == null || entity.getInsuranceTrailerId() == null || entity.getInsuranceTrailerId() == 0L) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null.");
		} else {
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			insuranceTrailerRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InsuranceTrailerM> delete(InsuranceTrailerM entity) {
		ObjectResult<InsuranceTrailerM> result = new ObjectResult<>();
		if (entity == null || entity.getInsuranceTrailerId() == null || entity.getInsuranceTrailerId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ClassifierValueM status = classifierValueRepository.findOne(Status.DELETED.getId());
			entity.setStatus(status);
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			insuranceTrailerRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<InsuranceTrailerM> lists(String companyName, String referenceCode, Long insuranceType,
			PageRequest pageRequest) {
		LOGGER.debug("companyName = "+companyName+" , referenceCode = "+referenceCode+" , insuranceType = "+insuranceType+" , pageRequest = "+pageRequest);
		Page<InsuranceTrailerM> results = null;
		if(StringUtils.isNotEmpty(companyName) && StringUtils.isNotEmpty(referenceCode) && insuranceType != null && insuranceType != 0L){
			results = insuranceTrailerRepository.findByCompanyNameAndReferenceCodeAndInsuranceType("%"+companyName+"%", "%"+referenceCode+"%", insuranceType, pageRequest);
		}else if(StringUtils.isNotEmpty(companyName) && StringUtils.isNotEmpty(referenceCode) && (insuranceType == null || insuranceType == 0L)){
			results = insuranceTrailerRepository.findByCompanyNameAndReferenceCode("%"+companyName+"%", "%"+referenceCode+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(companyName) && insuranceType != null && insuranceType != 0L && StringUtils.isEmpty(referenceCode)){
			results = insuranceTrailerRepository.findByCompanyNameAndInsuranceType("%"+companyName+"%", insuranceType, pageRequest);
		}else if(StringUtils.isNotEmpty(referenceCode) && insuranceType != null && insuranceType != 0L && StringUtils.isEmpty(companyName)){
			results = insuranceTrailerRepository.findByReferenceCodeAndInsuranceType("%"+referenceCode+"%", insuranceType, pageRequest);
		}else if(StringUtils.isNotEmpty(companyName) && StringUtils.isEmpty(referenceCode) && (insuranceType == null || insuranceType == 0L)){
			results = insuranceTrailerRepository.findByCompanyName("%"+companyName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(referenceCode) && StringUtils.isEmpty(companyName) && (insuranceType == null || insuranceType == 0L)){
			results = insuranceTrailerRepository.findByReferenceCode("%"+referenceCode+"%", pageRequest);
		}else if(insuranceType != null && insuranceType != 0L && StringUtils.isEmpty(referenceCode) && StringUtils.isEmpty(companyName)){
			results = insuranceTrailerRepository.findByInsuranceType(insuranceType, pageRequest);
		}else{
			results = insuranceTrailerRepository.findAll(pageRequest);
		}
		
		return results;
	}

	@Override
	public ObjectResult<List<InsuranceTrailerM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

}
