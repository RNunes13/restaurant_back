package br.com.nunes.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "product")
@ApiModel(value = "Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "ID")	
	@Column(name = "id", updatable = false, nullable = false, length = 11)
	private Long id;
	
	@ApiModelProperty(value = "Name")
	@Column(name = "name", nullable = false, length = 100)
	@Size(max = 60, message = "Name field can be up to 60 characters.")
	@NotEmpty(message = "Required 'name' field.")
	private String name;
	
	@ApiModelProperty(value = "Stock")
	@Column(name = "stock", nullable = false, length = 20)
	@NotNull(message = "Required 'stock' field.")
	private Long stock;

	@ApiModelProperty(value = "Unit")
	@Column(name = "Unit", nullable = false, length = 20)
	@Size(max = 20, message = "Unit field can be up to 20 characters.")
	@NotEmpty(message = "Required 'unit' field.")
	private String unit;
	
	@ApiModelProperty(value = "Description")
	@Column(name = "Description", nullable = false, length = 1000)
	@Size(max = 1000, message = "Description field can be up to 1000 characters.")
	@NotEmpty(message = "Required 'description' field.")
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

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
