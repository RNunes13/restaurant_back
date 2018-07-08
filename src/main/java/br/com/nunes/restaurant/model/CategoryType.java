package br.com.nunes.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "category_type")
@ApiModel(value = "Category Type")
public class CategoryType {
	
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

}
