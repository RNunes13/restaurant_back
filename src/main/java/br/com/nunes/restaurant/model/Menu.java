package br.com.nunes.restaurant.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Menu")
@Table(name = "menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "ID")
	@Column(name = "id", updatable = false, nullable = false, length = 11)
	private Long id;
	
	@ApiModelProperty(value = "Name")
	@Column(name = "name", nullable = false, length = 100)
	@Size(max = 100, message = "Name field can be up to 100 characters")
	@NotEmpty(message = "Required 'name' field.")
	private String name;
	
	@ApiModelProperty(value = "Price")
	@Column(name = "price", nullable = false, precision = 10, scale = 3)
	@DecimalMax("999999999.0")
	private BigDecimal price;
	
	@ApiModelProperty(value = "Category")
	@Column(name = "category", nullable = false, length = 60)
	@Size(max = 60, message = "Category field can be up to 60 characters")
	@NotEmpty(message = "Required 'category' field.")
	private String category;
	
	@ApiModelProperty(value = "Type")
	@Column(name = "type", nullable = false, length = 60)
	@Size(max = 100, message = "Type field can be up to 100 characters")
	@NotEmpty(message = "Required 'type' field.")
	private String type;
	
	@ApiModelProperty(value = "Description")
	@Column(name = "description", length = 1000)
	@Size(max = 1000, message = "Description field can be up to 1000 characters")
	private String description;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
