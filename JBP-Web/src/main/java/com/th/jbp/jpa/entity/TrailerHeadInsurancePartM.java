package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "JBP_TRAILER_HEAD_INSURANCE_PART")
public class TrailerHeadInsurancePartM implements Serializable{

	private static final long serialVersionUID = 1335241427138112390L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long trailerHeadInsurancePartId;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insurancePartsId", referencedColumnName = "insurancePartsId", nullable = false)
	protected InsurancePartsM insurancePart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trailerHeadId", referencedColumnName = "trailerHeadId", nullable = false)
	private TrailerHeadM trailerHead;
	
	public TrailerHeadInsurancePartM(){
		
	}

	public Long getTrailerHeadInsurancePartId() {
		return trailerHeadInsurancePartId;
	}

	public void setTrailerHeadInsurancePartId(Long trailerHeadInsurancePartId) {
		this.trailerHeadInsurancePartId = trailerHeadInsurancePartId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public InsurancePartsM getInsurancePart() {
		return insurancePart;
	}

	public void setInsurancePart(InsurancePartsM insurancePart) {
		this.insurancePart = insurancePart;
	}

	public ClassifierValueM getStatus() {
		return status;
	}

	public void setStatus(ClassifierValueM status) {
		this.status = status;
	}

	public TrailerHeadM getTrailerHead() {
		return trailerHead;
	}

	public void setTrailerHead(TrailerHeadM trailerHead) {
		this.trailerHead = trailerHead;
	}

}
