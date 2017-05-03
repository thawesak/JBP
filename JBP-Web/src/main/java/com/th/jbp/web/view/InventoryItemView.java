package com.th.jbp.web.view;

import java.io.Serializable;

public class InventoryItemView implements Serializable{

	private static final long serialVersionUID = -7522973649307049157L;

	private String name;
	
	private String itemStatus;
	
	private int quantity;
	
	private String remark1;
	
	private String remark2;
	
	public InventoryItemView(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
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
}
