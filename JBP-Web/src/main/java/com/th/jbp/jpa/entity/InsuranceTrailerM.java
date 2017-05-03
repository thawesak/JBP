package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JBP_INSURANCE_TRAILER")
public class InsuranceTrailerM implements Serializable{

	private static final long serialVersionUID = -7615865258339942955L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long insuranceTrailerId;
	
	@Column
	private String referenceCode;
	
	@Column
	private String companyName;
	
	@Column
	private String description;
	
	@Column
	private String remark;
	
	@Column
	private int level;
	
	@Column
	private String companyTel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insuranceType", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM insuranceType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM status;
	
	@Column
	private Timestamp createDate;
	
	@Column
	private Timestamp updateDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createBy", referencedColumnName = "userId", nullable = false)
	protected UserM createBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updateBy", referencedColumnName = "userId", nullable = true)
	protected UserM updateBy;

	public InsuranceTrailerM(){
		
	}

	public Long getInsuranceTrailerId() {
		return insuranceTrailerId;
	}

	public void setInsuranceTrailerId(Long insuranceTrailerId) {
		this.insuranceTrailerId = insuranceTrailerId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public ClassifierValueM getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(ClassifierValueM insuranceType) {
		this.insuranceType = insuranceType;
	}

	public ClassifierValueM getStatus() {
		return status;
	}

	public void setStatus(ClassifierValueM status) {
		this.status = status;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public UserM getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UserM createBy) {
		this.createBy = createBy;
	}

	public UserM getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(UserM updateBy) {
		this.updateBy = updateBy;
	}
}
