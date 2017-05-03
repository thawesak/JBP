package com.th.jbp.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JBP_USER")
public class UserM implements Serializable{

	private static final long serialVersionUID = -3521037193316162825L;
	
	@Id
	private Long userId;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private String firstName;

	@Column
	private String lastName;
	
	@Column
	private String password;
	
	@Column
	private BigDecimal salary;
	
	@Column(unique=true)
	private String cardId;
	
	@Column(unique=true)
	private String driverCardId;
	
	@Column
	private String tel;
	
	@Column
	private String address;
	
	@Column
	private String mobileNo;
	
	@Column
	private java.sql.Date startDate;
	
	@Column
	private java.sql.Date birthday; 
	
	@Column
	private String email;
	
	@Column
	private Timestamp createDate;
	
	@Column
	private Timestamp updateDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "JBP_USER_ROLE", joinColumns = {
			@JoinColumn(name = "USERID", referencedColumnName = "USERID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID") })
	protected Set<RoleM> roles;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "classifierValueId", nullable = false)
	protected ClassifierValueM status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch", referencedColumnName = "classifierValueId", nullable = true)
	protected ClassifierValueM branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId", referencedColumnName = "companyId", nullable = false)
	protected CompanyM company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createBy", referencedColumnName = "userId", nullable = false)
	protected UserM createBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updateBy", referencedColumnName = "userId", nullable = true)
	protected UserM updateBy;
	
	private Timestamp lastLoggedIn;
	
	@Column(name="IMAGE_PROFILE")
	private byte[] imageProfile;
	
	@Column(name="IMAGE_CARD")
	private byte[] imageCard;
	
	private String sex;
	
	public UserM() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getDriverCardId() {
		return driverCardId;
	}

	public void setDriverCardId(String driverCardId) {
		this.driverCardId = driverCardId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
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

	public Set<RoleM> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleM> roles) {
		this.roles = roles;
	}

	public ClassifierValueM getStatus() {
		return status;
	}

	public void setStatus(ClassifierValueM status) {
		this.status = status;
	}

	public ClassifierValueM getBranch() {
		return branch;
	}

	public void setBranch(ClassifierValueM branch) {
		this.branch = branch;
	}

	public CompanyM getCompany() {
		return company;
	}

	public void setCompany(CompanyM company) {
		this.company = company;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Timestamp getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(Timestamp lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(byte[] imageProfile) {
		this.imageProfile = imageProfile;
	}

	public byte[] getImageCard() {
		return imageCard;
	}

	public void setImageCard(byte[] imageCard) {
		this.imageCard = imageCard;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
