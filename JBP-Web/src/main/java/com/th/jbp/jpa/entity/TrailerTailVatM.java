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
@Table(name = "JBP_TRAILER_TAIL_VAT")
public class TrailerTailVatM implements Serializable{

	private static final long serialVersionUID = 5017527743896980886L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long trailerTailVatId;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trailerTailId", referencedColumnName = "trailerTailId", nullable = false)
	private TrailerTailM trailerTail;
	
	public TrailerTailVatM(){
		
	}

	public Long getTrailerTailVatId() {
		return trailerTailVatId;
	}

	public void setTrailerTailVatId(Long trailerTailVatId) {
		this.trailerTailVatId = trailerTailVatId;
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

	public ClassifierValueM getStatus() {
		return status;
	}

	public void setStatus(ClassifierValueM status) {
		this.status = status;
	}

	public TrailerTailM getTrailerTail() {
		return trailerTail;
	}

	public void setTrailerTail(TrailerTailM trailerTail) {
		this.trailerTail = trailerTail;
	}

}
