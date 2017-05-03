package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the JBP_SYSTEM_CONFIG database table.
 * 
 */
@Entity
@Table(name = "JBP_SYSTEM_CONFIG")
@NamedQuery(name = "SystemConfigM.findAll", query = "SELECT b FROM SystemConfigM b")
public class SystemConfigM implements Serializable {
	private static final long serialVersionUID = -8500901313787251258L;

	@Id
	@Column(name = "SYSTEM_KEY")
	private String systemKey;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@Column(name = "SYSTEM_VALUE")
	private String systemValue;

	@Column(name = "UPDATE_DATE")
	private Timestamp updateDate;
	
	@ManyToOne
	@JoinColumn(name = "CREATE_BY", referencedColumnName = "userId", nullable = false)
	protected UserM createBy;
	
	@ManyToOne
	@JoinColumn(name = "UPDATE_BY", referencedColumnName = "userId", nullable = false)
	protected UserM updateBy;

	@Transient
	private boolean editingStatus = false;

	public SystemConfigM() {
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
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

	public String getSystemValue() {
		return systemValue;
	}

	public void setSystemValue(String systemValue) {
		this.systemValue = systemValue;
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

	public boolean isEditingStatus() {
		return editingStatus;
	}

	public void setEditingStatus(boolean editingStatus) {
		this.editingStatus = editingStatus;
	}

}