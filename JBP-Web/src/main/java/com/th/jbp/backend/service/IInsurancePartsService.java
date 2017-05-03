package com.th.jbp.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.InsurancePartsItemM;
import com.th.jbp.jpa.entity.InsurancePartsM;

public interface IInsurancePartsService extends IService<InsurancePartsM>{

	public Page<InsurancePartsM> lists(String companyName, String referenceCode, PageRequest pageRequest);
	
	public ObjectResult<List<InsurancePartsItemM>> findPartsItemByInsurancePartsId(Long id);

}
