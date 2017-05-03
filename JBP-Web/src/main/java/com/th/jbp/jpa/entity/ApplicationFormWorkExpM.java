package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name="JBP_APPLICATION_FORM_WORK_EXP")
public class ApplicationFormWorkExpM implements Serializable{

	private static final long serialVersionUID = 4653813392433251989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long applicationFormWorkExpId;
	
	@Column(nullable = true)
	private String companyName;
	
	@Column(nullable = true)
	private String startDate;
	
	@Column(nullable = true)
	private String endDate;
	
	@Column(nullable = true)
	private String position;
	
	@Column(nullable = true)
	private String jobDescription;
	
	@Column(nullable = true)
	private BigDecimal salary;
	
	@Column(nullable = true)
	private String reasonResign;
	
	@Column(nullable = true)
	private String tel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "applicationFormId", referencedColumnName = "applicationFormId", nullable = false)
	private ApplicationFormM applicationForm;
	
	public ApplicationFormWorkExpM(){
		
	}

	public Long getApplicationFormWorkExpId() {
		return applicationFormWorkExpId;
	}

	public void setApplicationFormWorkExpId(Long applicationFormWorkExpId) {
		this.applicationFormWorkExpId = applicationFormWorkExpId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getReasonResign() {
		return reasonResign;
	}

	public void setReasonResign(String reasonResign) {
		this.reasonResign = reasonResign;
	}

	public ApplicationFormM getApplicationForm() {
		return applicationForm;
	}

	public void setApplicationForm(ApplicationFormM applicationForm) {
		this.applicationForm = applicationForm;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
