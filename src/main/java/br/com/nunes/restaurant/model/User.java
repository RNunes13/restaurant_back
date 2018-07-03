package br.com.nunes.restaurant.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user")
@ApiModel(value = "User")
public class User  {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "ID")	
	@Column(name = "id", updatable = false, nullable = false, length = 11)
	private Long id;

	@ApiModelProperty(value = "Name")
	@Column(name = "name", nullable = false, length = 60)
	@Size(max = 60, message = "Name field can be up to 60 characters.")
	@NotEmpty(message = "Required 'name' field.")
	private String name;
	
	@ApiModelProperty(value = "Email")
	@Column(name = "email", unique = true, nullable = false, length = 100)
	@Size(max = 100, message = "Email field can be up to 100 characters.")
	@NotEmpty(message = "Required 'email' field.")
	@Email(message = "Email field must be a valid email address.")
	private String email;
	
	@ApiModelProperty(value = "Username")
	@Column(name = "username", unique = true, nullable = false, length = 20)
	@Size(max = 20, message = "Username field can be up to 20 characters.")
	@NotEmpty(message = "Required 'username' field.")
	private String username;
	
	@ApiModelProperty(value = "Password")
	@Column(name = "password", nullable = false, length = 100)
	@Size(max = 100, message = "Password field can be up to 100 characters.")
	@NotEmpty(message = "Required 'password' field.")
	private String password;
	
	@ApiModelProperty(value = "Rule (1 - Administrator, 2 - Waiter, 3 - Cook, 4 - Cashier, 5 - Customer)")
	@Column(name = "rule", nullable = false, length = 20)
	@NotNull(message = "Required 'rule' field.")
	private Long rule;
	
	@ApiModelProperty(value = "Status (0 - Inactive, 1 - Active)")
	@Column(name = "status", nullable = false, length = 20)
	@NotNull(message = "Required 'status' field.")
	private Long status;

	@CreationTimestamp
	@ApiModelProperty(value = "Creation date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt" , updatable = false, nullable = false)
	private Calendar createdAt;
	
	@UpdateTimestamp
	@ApiModelProperty(value = "Updated date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt" , nullable = false)
	private Calendar updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRule() {
		return rule;
	}

	public void setRule(Long rule) {
		this.rule = rule;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

}
