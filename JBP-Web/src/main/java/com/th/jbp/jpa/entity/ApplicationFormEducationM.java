package com.th.jbp.jpa.entity;

import java.io.Serializable;

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
@Table(name="JBP_APPLICATION_FORM_EDUCATION")
public class ApplicationFormEducationM implements Serializable{

	private static final long serialVersionUID = 438184910222801964L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long applicationFormEducationId;
	
	@Column(nullable = true)
	private String level;
	
	@Column(nullable = true)
	private String institution;
	
	@Column(nullable = true)
	private String major;
	
	@Column(nullable = true, name="STUDY_FROM")
	private String studyFrom;
	
	@Column(nullable = true, name="STUDY_TO")
	private String studyTo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "applicationFormId", referencedColumnName = "applicationFormId", nullable = false)
	private ApplicationFormM applicationForm;
	
	public ApplicationFormEducationM(){
		
	}

	public Long getApplicationFormEducationId() {
		return applicationFormEducationId;
	}

	public void setApplicationFormEducationId(Long applicationFormEducationId) {
		this.applicationFormEducationId = applicationFormEducationId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public ApplicationFormM getApplicationForm() {
		return applicationForm;
	}

	public void setApplicationForm(ApplicationFormM applicationForm) {
		this.applicationForm = applicationForm;
	}

	public String getStudyFrom() {
		return studyFrom;
	}

	public void setStudyFrom(String studyFrom) {
		this.studyFrom = studyFrom;
	}

	public String getStudyTo() {
		return studyTo;
	}

	public void setStudyTo(String studyTo) {
		this.studyTo = studyTo;
	}

}
