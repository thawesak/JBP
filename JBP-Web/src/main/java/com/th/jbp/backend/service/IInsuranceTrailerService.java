package com.th.jbp.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.jpa.entity.InsuranceTrailerM;

public interface IInsuranceTrailerService extends IService<InsuranceTrailerM>{

	public Page<InsuranceTrailerM> lists(String companyName, String referenceCode, Long insuranceType, PageRequest pageRequest);

}
