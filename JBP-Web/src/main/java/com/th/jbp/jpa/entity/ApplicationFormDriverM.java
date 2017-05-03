package com.th.jbp.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="JBP_APPLICATION_FORM_DRIVER")
public class ApplicationFormDriverM implements Serializable{

	private static final long serialVersionUID = 641031127818911236L;
	
	@Id
	private Long applicationFormId;
	
	@Column(name="IMAGE_DRIVER_CARD")
	private byte[] imageDriverCard;
	
	@Column(name="IMAGE_HEALTH_CHECK1")
	private byte[] imageHealthCheck1;
	
	@Column(name="IMAGE_HEALTH_CHECK2")
	private byte[] imageHealthCheck2;
	
	@Column(name="IMAGE_HEALTH_CHECK3")
	private byte[] imageHealthCheck3;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean questionNo1_1;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean questionNo1_2;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean questionNo1_3;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean questionNo1_4;
	
	@Column
	private String questionNo2;
	
	@Column
	private String questionNo3;
	
	@Column
	private String questionNo3_1;
	
	@Column
	private String questionNo3_2;
	
	@Column
	private String questionNo4_1;
	
	@Column
	private int questionNo4_2;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean questionNo5_1;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean questionNo5_2;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean questionNo5_3;
	
	@Column
	private String questionNo5_3_desc;
	
	@Column
	private String questionNo6;
	
	@Column
	private String questionNo7;
	
	@Column
	private String questionNo8;
	
	@Column
	private String questionNo9;
	
	@Column
	private String questionNo10;
	
	@Column
	private String questionNo11;
	
	@Column
	private String questionNo11_desc;
	
	@Column
	private String questionNo12;
	
	@Column
	private String questionNo13;
	
	@Column
	private String questionNo13_desc;
	
	@Column
	private String questionNo14;
	
	@Column
	private java.sql.Date questionNo14_date;
	
	@Column
	private String questionNo14_desc;
	
	@Column
	private String questionNo15;
	
	@Column
	private String questionNo16;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private ApplicationFormM applicationForm;
	
	public ApplicationFormDriverM(){
		
	}

	public Long getApplicationFormId() {
		return applicationFormId;
	}

	public void setApplicationFormId(Long applicationFormId) {
		this.applicationFormId = applicationFormId;
	}

	public boolean isQuestionNo1_1() {
		return questionNo1_1;
	}

	public void setQuestionNo1_1(boolean questionNo1_1) {
		this.questionNo1_1 = questionNo1_1;
	}

	public boolean isQuestionNo1_2() {
		return questionNo1_2;
	}

	public void setQuestionNo1_2(boolean questionNo1_2) {
		this.questionNo1_2 = questionNo1_2;
	}

	public boolean isQuestionNo1_3() {
		return questionNo1_3;
	}

	public void setQuestionNo1_3(boolean questionNo1_3) {
		this.questionNo1_3 = questionNo1_3;
	}

	public boolean isQuestionNo1_4() {
		return questionNo1_4;
	}

	public void setQuestionNo1_4(boolean questionNo1_4) {
		this.questionNo1_4 = questionNo1_4;
	}

	public String getQuestionNo2() {
		return questionNo2;
	}

	public void setQuestionNo2(String questionNo2) {
		this.questionNo2 = questionNo2;
	}

	public String getQuestionNo3() {
		return questionNo3;
	}

	public void setQuestionNo3(String questionNo3) {
		this.questionNo3 = questionNo3;
	}

	public String getQuestionNo3_1() {
		return questionNo3_1;
	}

	public void setQuestionNo3_1(String questionNo3_1) {
		this.questionNo3_1 = questionNo3_1;
	}

	public String getQuestionNo3_2() {
		return questionNo3_2;
	}

	public void setQuestionNo3_2(String questionNo3_2) {
		this.questionNo3_2 = questionNo3_2;
	}

	public String getQuestionNo4_1() {
		return questionNo4_1;
	}

	public void setQuestionNo4_1(String questionNo4_1) {
		this.questionNo4_1 = questionNo4_1;
	}

	public int getQuestionNo4_2() {
		return questionNo4_2;
	}

	public void setQuestionNo4_2(int questionNo4_2) {
		this.questionNo4_2 = questionNo4_2;
	}

	public boolean getQuestionNo5_1() {
		return questionNo5_1;
	}

	public void setQuestionNo5_1(boolean questionNo5_1) {
		this.questionNo5_1 = questionNo5_1;
	}

	public boolean getQuestionNo5_2() {
		return questionNo5_2;
	}

	public void setQuestionNo5_2(boolean questionNo5_2) {
		this.questionNo5_2 = questionNo5_2;
	}

	public boolean getQuestionNo5_3() {
		return questionNo5_3;
	}

	public void setQuestionNo5_3(boolean questionNo5_3) {
		this.questionNo5_3 = questionNo5_3;
	}

	public String getQuestionNo5_3_desc() {
		return questionNo5_3_desc;
	}

	public void setQuestionNo5_3_desc(String questionNo5_3_desc) {
		this.questionNo5_3_desc = questionNo5_3_desc;
	}

	public String getQuestionNo6() {
		return questionNo6;
	}

	public void setQuestionNo6(String questionNo6) {
		this.questionNo6 = questionNo6;
	}

	public String getQuestionNo7() {
		return questionNo7;
	}

	public void setQuestionNo7(String questionNo7) {
		this.questionNo7 = questionNo7;
	}

	public String getQuestionNo8() {
		return questionNo8;
	}

	public void setQuestionNo8(String questionNo8) {
		this.questionNo8 = questionNo8;
	}

	public String getQuestionNo9() {
		return questionNo9;
	}

	public void setQuestionNo9(String questionNo9) {
		this.questionNo9 = questionNo9;
	}

	public String getQuestionNo10() {
		return questionNo10;
	}

	public void setQuestionNo10(String questionNo10) {
		this.questionNo10 = questionNo10;
	}

	public String getQuestionNo11() {
		return questionNo11;
	}

	public void setQuestionNo11(String questionNo11) {
		this.questionNo11 = questionNo11;
	}

	public String getQuestionNo11_desc() {
		return questionNo11_desc;
	}

	public void setQuestionNo11_desc(String questionNo11_desc) {
		this.questionNo11_desc = questionNo11_desc;
	}

	public String getQuestionNo12() {
		return questionNo12;
	}

	public void setQuestionNo12(String questionNo12) {
		this.questionNo12 = questionNo12;
	}

	public String getQuestionNo13() {
		return questionNo13;
	}

	public void setQuestionNo13(String questionNo13) {
		this.questionNo13 = questionNo13;
	}

	public String getQuestionNo13_desc() {
		return questionNo13_desc;
	}

	public void setQuestionNo13_desc(String questionNo13_desc) {
		this.questionNo13_desc = questionNo13_desc;
	}

	public String getQuestionNo14() {
		return questionNo14;
	}

	public void setQuestionNo14(String questionNo14) {
		this.questionNo14 = questionNo14;
	}

	public java.sql.Date getQuestionNo14_date() {
		return questionNo14_date;
	}

	public void setQuestionNo14_date(java.sql.Date questionNo14_date) {
		this.questionNo14_date = questionNo14_date;
	}

	public String getQuestionNo14_desc() {
		return questionNo14_desc;
	}

	public void setQuestionNo14_desc(String questionNo14_desc) {
		this.questionNo14_desc = questionNo14_desc;
	}

	public String getQuestionNo15() {
		return questionNo15;
	}

	public void setQuestionNo15(String questionNo15) {
		this.questionNo15 = questionNo15;
	}

	public String getQuestionNo16() {
		return questionNo16;
	}

	public void setQuestionNo16(String questionNo16) {
		this.questionNo16 = questionNo16;
	}

	public ApplicationFormM getApplicationForm() {
		return applicationForm;
	}

	public void setApplicationForm(ApplicationFormM applicationForm) {
		this.applicationForm = applicationForm;
	}

	public byte[] getImageDriverCard() {
		return imageDriverCard;
	}

	public void setImageDriverCard(byte[] imageDriverCard) {
		this.imageDriverCard = imageDriverCard;
	}

	public byte[] getImageHealthCheck1() {
		return imageHealthCheck1;
	}

	public void setImageHealthCheck1(byte[] imageHealthCheck1) {
		this.imageHealthCheck1 = imageHealthCheck1;
	}

	public byte[] getImageHealthCheck2() {
		return imageHealthCheck2;
	}

	public void setImageHealthCheck2(byte[] imageHealthCheck2) {
		this.imageHealthCheck2 = imageHealthCheck2;
	}

	public byte[] getImageHealthCheck3() {
		return imageHealthCheck3;
	}

	public void setImageHealthCheck3(byte[] imageHealthCheck3) {
		this.imageHealthCheck3 = imageHealthCheck3;
	}

}
