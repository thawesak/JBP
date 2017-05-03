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

import com.th.jbp.web.view.PartsItemWarrantyView;

@Entity
@Table(name="JBP_INSURANCE_PARTS_ITEM")
public class InsurancePartsItemM implements Serializable{

	private static final long serialVersionUID = 6584652311607991650L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long insurancePartsItemId;
	
	@Column
	private double warrantyYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemType", referencedColumnName = "inventoryItemTypeId", nullable = false)
	protected InventoryItemTypeM itemType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insurancePartsId", referencedColumnName = "insurancePartsId", nullable = false)
	protected InsurancePartsM insuranceParts;
	
	public InsurancePartsItemM(){
		
	}
	
	public PartsItemWarrantyView getPartsItemWarrantyView(){
		PartsItemWarrantyView result = new PartsItemWarrantyView();
		result.setPartsItemName(this.itemType.getName());
		result.setWarrantyYear(this.warrantyYear);
		
		return result;
	}

	public Long getInsurancePartsItemId() {
		return insurancePartsItemId;
	}

	public void setInsurancePartsItemId(Long insurancePartsItemId) {
		this.insurancePartsItemId = insurancePartsItemId;
	}

	public double getWarrantyYear() {
		return warrantyYear;
	}

	public void setWarrantyYear(double warrantyYear) {
		this.warrantyYear = warrantyYear;
	}

	public InventoryItemTypeM getItemType() {
		return itemType;
	}

	public void setItemType(InventoryItemTypeM itemType) {
		this.itemType = itemType;
	}

	public InsurancePartsM getInsuranceParts() {
		return insuranceParts;
	}

	public void setInsuranceParts(InsurancePartsM insuranceParts) {
		this.insuranceParts = insuranceParts;
	}
	
}
