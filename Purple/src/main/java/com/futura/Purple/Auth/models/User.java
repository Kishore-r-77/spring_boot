package com.futura.Purple.Auth.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.futura.Purple.Entity.Company;
import com.futura.Purple.Entity.Permissions;
import com.futura.Purple.Entity.UserGroup;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@Lob
	private String profile;

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	private String verificationCode;

	@NotBlank
	@Size(max = 120)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

	private Long userGroupId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userGroupId", insertable = false, updatable = false)
	private UserGroup userGroup;


	private int validFlag;

	public int getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	private Long companyId;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "companyId", updatable = false, insertable = false)
	private Company company;

	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Permissions> specialAccess;

	public User() {

	}

	public User(String username, String email, String password, Long userGroupId, Long companyId, String profile) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.userGroupId = userGroupId;
		this.companyId = companyId;
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Permissions> getSpecialAccess() {
		return specialAccess;
	}

	public void setSpecialAccess(List<Permissions> specialAccess) {
		this.specialAccess = specialAccess;
	}

	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}
