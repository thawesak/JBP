package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "JBP_TRAILER_HEAD_INSURANCE")
public class TrailerHeadInsuranceM implements Serializable{

	private static final long serialVersionUID = -1150767518564733865L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long trailerHeadInsuranceId;
	
	@Column
	private String policyholdersNumber; //เลขกรมธรรม์
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column
	private BigDecimal amount; //ทุนประกัน
	
	@Column
	private BigDecimal insurancePremium; //เบี้ยประกัน
	
	@Column
	private BigDecimal premiumsPaid; //เบี้ยจ่าย
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insuranceTrailerId", referencedColumnName = "insuranceTrailerId", nullable = false)
	protected InsuranceTrailerM insuranceTrailer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trailerHeadId", referencedColumnName = "trailerHeadId", nullable = false)
	private TrailerHeadM trailerHead;
	
	public TrailerHeadInsuranceM(){
		
	}

	public Long getTrailerHeadInsuranceId() {
		return trailerHeadInsuranceId;
	}

	public void setTrailerHeadInsuranceId(Long trailerHeadInsuranceId) {
		this.trailerHeadInsuranceId = trailerHeadInsuranceId;
	}

	public String getPolicyholdersNumber() {
		return policyholdersNumber;
	}

	public void setPolicyholdersNumber(String policyholdersNumber) {
		this.policyholdersNumber = policyholdersNumber;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getInsurancePremium() {
		return insurancePremium;
	}

	public void setInsurancePremium(BigDecimal insurancePremium) {
		this.insurancePremium = insurancePremium;
	}

	public BigDecimal getPremiumsPaid() {
		return premiumsPaid;
	}

	public void setPremiumsPaid(BigDecimal premiumsPaid) {
		this.premiumsPaid = premiumsPaid;
	}

	public InsuranceTrailerM getInsuranceTrailer() {
		return insuranceTrailer;
	}

	public void setInsuranceTrailer(InsuranceTrailerM insuranceTrailer) {
		this.insuranceTrailer = insuranceTrailer;
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
