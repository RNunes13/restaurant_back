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
@Table(name = "component")
@ApiModel(value = "Component")
public class Component {

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
	
	@ApiModelProperty(value = "Note")
	@Column(name = "note", length = 200)
	@Size(max = 200, message = "Note field can be up to 200 characters.")
	private String note;
	
	@ApiModelProperty(value = "Icon")
	@Column(name = "icon", nullable = false, length = 100)
	@Size(max = 100, message = "Icon field can be up to 100 characters.")
	@NotEmpty(message = "Required 'icon' field.")
	private String icon;
	
	@ApiModelProperty(value = "URL")
	@Column(name = "url", nullable = false, length = 100)
	@Size(max = 100, message = "Note field can be up to 100 characters.")
	@NotEmpty(message = "Required 'url' field.")
	private String url;

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
}
