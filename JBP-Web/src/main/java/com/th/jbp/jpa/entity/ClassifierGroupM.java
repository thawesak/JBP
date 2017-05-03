package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "ClassifierGroupM.findByName", query = "select o from ClassifierGroupM o where o.name = :name ")
@Table(name="JBP_CLASSIFIERGROUP")
public class ClassifierGroupM implements Serializable {

	private static final long serialVersionUID = -358983704542642086L;

	@Id
	@Column(nullable = false)
	private Long classifierGroupId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private Timestamp createDate;
	
	@Column(nullable = true)
	private Timestamp updateDate;
	
	@OneToMany(mappedBy="classifierGroup", fetch = FetchType.LAZY)
	private List<ClassifierValueM> classifierValues = new ArrayList<ClassifierValueM>();

	public ClassifierGroupM() {
	}

	public Long getClassifierGroupId() {
		return classifierGroupId;
	}

	public void setClassifierGroupId(Long classifierGroupId) {
		this.classifierGroupId = classifierGroupId;
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

	public List<ClassifierValueM> getClassifierValues() {
		return classifierValues;
	}

	public void setClassifierValues(List<ClassifierValueM> classifierValues) {
		this.classifierValues = classifierValues;
	}

}
