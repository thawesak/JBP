package com.th.jbp.web.view;

import java.io.Serializable;

public class PartsItemWarrantyView implements Serializable{

	private static final long serialVersionUID = 4297346505002764843L;
	
	private String partsItemName = null;
	private double warrantyYear = 0;
	
	public PartsItemWarrantyView(){
		
	}

	public String getPartsItemName() {
		return partsItemName;
	}

	public void setPartsItemName(String partsItemName) {
		this.partsItemName = partsItemName;
	}

	public double getWarrantyYear() {
		return warrantyYear;
	}

	public void setWarrantyYear(double warrantyYear) {
		this.warrantyYear = warrantyYear;
	}

}
