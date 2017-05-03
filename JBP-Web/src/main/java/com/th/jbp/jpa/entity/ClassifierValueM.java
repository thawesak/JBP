package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JBP_CLASSIFIERVALUE")
public class ClassifierValueM implements Serializable {

	private static final long serialVersionUID = -2347755269964886511L;

	@Id
	@Column(nullable = false)
	private Long classifierValueId;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;

	@Column(nullable = true)
	private Timestamp createDate;

	@Column(nullable = true)
	private Timestamp updateDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classifierGroupId", referencedColumnName = "classifierGroupId", nullable = false)
	private ClassifierGroupM classifierGroup;

	public ClassifierValueM() {

	}

	public Long getClassifierValueId() {
		return classifierValueId;
	}

	public void setClassifierValueId(Long classifierValueId) {
		this.classifierValueId = classifierValueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public ClassifierGroupM getClassifierGroup() {
		return classifierGroup;
	}

	public void setClassifierGroup(ClassifierGroupM classifierGroup) {
		this.classifierGroup = classifierGroup;
	}

}
