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
@Table(name="JBP_INVENTORY_ITEM_TYPE")
public class InventoryItemTypeM implements Serializable{

	private static final long serialVersionUID = -7604222225810051429L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long inventoryItemTypeId;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String prefix;
	
	@Column
	private String unit;
	
	@Column
	private String remark1;
	
	@Column
	private String remark2;

	@Column
	private int minimumOrder;
	
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
	
	public InventoryItemTypeM(){
		
	}

	public Long getInventoryItemTypeId() {
		return inventoryItemTypeId;
	}

	public void setInventoryItemTypeId(Long inventoryItemTypeId) {
		this.inventoryItemTypeId = inventoryItemTypeId;
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public int getMinimumOrder() {
		return minimumOrder;
	}

	public void setMinimumOrder(int minimumOrder) {
		this.minimumOrder = minimumOrder;
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
