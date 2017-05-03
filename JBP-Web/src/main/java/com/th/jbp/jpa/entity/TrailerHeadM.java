package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "JBP_TRAILER_HEAD")
public class TrailerHeadM implements Serializable{

	private static final long serialVersionUID = 2808615863344469932L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long trailerHeadId;
	
	@Column
	private String licensePlate;
	
	@Column
	private String brand;
	
	@Column
	private String model;
	
	@Column
	private int weight;
	
	@Column
	private Integer mileNumber;
	
	@Column
	private String chassisNumber;
	
	@Column
	private String engineNumber;
	
	@Column
	private BigDecimal price;
	
	@Column
	private BigDecimal installmentPrice;
	
	@Column
	private BigDecimal installmentPeriodPrice;
	
	@Column
	private int installmentPeriod;
	
	@Column
	private BigDecimal installmentInterestPercent;
	
	@Column
	private Date purchaseDate;
	
	@Column
	private String purchaseAt;
	
	@Column
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean passport;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyRegisterId", referencedColumnName = "companyId", nullable = false)
	protected CompanyM companyRegister;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyIncomeId", referencedColumnName = "companyId")
	protected CompanyM companyIncome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driverId", referencedColumnName = "userId")
	protected UserM driver;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "JBP_TRAILER_HEAD_INVENTORY_ITEM", joinColumns = {
			@JoinColumn(name = "trailerHeadId", referencedColumnName = "trailerHeadId") }, inverseJoinColumns = {
					@JoinColumn(name = "inventoryItemId", referencedColumnName = "inventoryItemId") })
	protected Set<InventoryItemM> inventoryItems;
	
	@OneToMany(mappedBy="trailerHead", fetch = FetchType.LAZY)
	private List<TrailerHeadInsuranceM> insurances = new ArrayList<TrailerHeadInsuranceM>();
	
	@OneToMany(mappedBy="trailerHead", fetch = FetchType.LAZY)
	private List<TrailerHeadInsurancePartM> insuranceParts = new ArrayList<TrailerHeadInsurancePartM>();
	
	@OneToMany(mappedBy="trailerHead", fetch = FetchType.LAZY)
	private List<TrailerHeadVatM> vats = new ArrayList<TrailerHeadVatM>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "JBP_TRAILER_HEAD_TAIL", joinColumns = {
			@JoinColumn(name = "trailerHeadId", referencedColumnName = "trailerHeadId") }, inverseJoinColumns = {
					@JoinColumn(name = "trailerTailId", referencedColumnName = "trailerTailId") })
	protected Set<TrailerTailM> trailerTails;
	
	@Column
	private String remark;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM status;
	
	public TrailerHeadM(){
		
	}

	public Long getTrailerHeadId() {
		return trailerHeadId;
	}

	public void setTrailerHeadId(Long trailerHeadId) {
		this.trailerHeadId = trailerHeadId;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Integer getMileNumber() {
		return mileNumber;
	}

	public void setMileNumber(Integer mileNumber) {
		this.mileNumber = mileNumber;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getInstallmentPrice() {
		return installmentPrice;
	}

	public void setInstallmentPrice(BigDecimal installmentPrice) {
		this.installmentPrice = installmentPrice;
	}

	public BigDecimal getInstallmentPeriodPrice() {
		return installmentPeriodPrice;
	}

	public void setInstallmentPeriodPrice(BigDecimal installmentPeriodPrice) {
		this.installmentPeriodPrice = installmentPeriodPrice;
	}

	public int getInstallmentPeriod() {
		return installmentPeriod;
	}

	public void setInstallmentPeriod(int installmentPeriod) {
		this.installmentPeriod = installmentPeriod;
	}

	public BigDecimal getInstallmentInterestPercent() {
		return installmentInterestPercent;
	}

	public void setInstallmentInterestPercent(BigDecimal installmentInterestPercent) {
		this.installmentInterestPercent = installmentInterestPercent;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseAt() {
		return purchaseAt;
	}

	public void setPurchaseAt(String purchaseAt) {
		this.purchaseAt = purchaseAt;
	}

	public boolean isPassport() {
		return passport;
	}

	public void setPassport(boolean passport) {
		this.passport = passport;
	}

	public CompanyM getCompanyRegister() {
		return companyRegister;
	}

	public void setCompanyRegister(CompanyM companyRegister) {
		this.companyRegister = companyRegister;
	}

	public CompanyM getCompanyIncome() {
		return companyIncome;
	}

	public void setCompanyIncome(CompanyM companyIncome) {
		this.companyIncome = companyIncome;
	}

	public UserM getDriver() {
		return driver;
	}

	public void setDriver(UserM driver) {
		this.driver = driver;
	}

	public Set<InventoryItemM> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(Set<InventoryItemM> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public ClassifierValueM getStatus() {
		return status;
	}

	public void setStatus(ClassifierValueM status) {
		this.status = status;
	}

	public List<TrailerHeadInsuranceM> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<TrailerHeadInsuranceM> insurances) {
		this.insurances = insurances;
	}

	public List<TrailerHeadInsurancePartM> getInsuranceParts() {
		return insuranceParts;
	}

	public void setInsuranceParts(List<TrailerHeadInsurancePartM> insuranceParts) {
		this.insuranceParts = insuranceParts;
	}

	public List<TrailerHeadVatM> getVats() {
		return vats;
	}

	public void setVats(List<TrailerHeadVatM> vats) {
		this.vats = vats;
	}

	public Set<TrailerTailM> getTrailerTails() {
		return trailerTails;
	}

	public void setTrailerTails(Set<TrailerTailM> trailerTails) {
		this.trailerTails = trailerTails;
	}
	
}
