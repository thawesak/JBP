package com.th.jbp.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JBP_COMPANY")
public class CompanyM implements Serializable{

	private static final long serialVersionUID = -5023544430710030044L;
	
	@Id
	@Column
	private Long companyId;
	
	@Column
	private String companyName;
	
	@Column
	private String symbol;
	
	@ManyToOne
	@JoinColumn(name = "status", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM status;
	
	@ManyToOne
	@JoinColumn(name = "type", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM type;
	
	public CompanyM(){
		
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public ClassifierValueM getStatus() {
		return status;
	}

	public void setStatus(ClassifierValueM status) {
		this.status = status;
	}

	public ClassifierValueM getType() {
		return type;
	}

	public void setType(ClassifierValueM type) {
		this.type = type;
	}

}
