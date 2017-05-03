package com.th.jbp.web.view;

import java.io.Serializable;

public class InsurancePartsView implements Serializable{
	private static final long serialVersionUID = -5576149778129668365L;
	
	private Long insurancePartsId;
	private String referenceCode;
	private String companyName;
	private String warrantyDesc;

	public InsurancePartsView(){
		
	}

	public Long getInsurancePartsId() {
		return insurancePartsId;
	}

	public void setInsurancePartsId(Long insurancePartsId) {
		this.insurancePartsId = insurancePartsId;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWarrantyDesc() {
		return warrantyDesc;
	}

	public void setWarrantyDesc(String warrantyDesc) {
		this.warrantyDesc = warrantyDesc;
	}
	
}
