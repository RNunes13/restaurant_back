package br.com.nunes.restaurant.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "menu_image")
@ApiModel(value = "Menu Image")
public class MenuImage {
	
	@EmbeddedId
	private MenuImageCompl menuImageCompl;
	
	@ApiModelProperty(value = "Image")
	@Column(name = "image", nullable = false)
	@NotEmpty(message = "Required 'image' field.")
	@Lob
	private byte[] image;

	public MenuImageCompl getMenuImageCompl() {
		return menuImageCompl;
	}

	public void setMenuImageCompl(MenuImageCompl menuImageCompl) {
		this.menuImageCompl = menuImageCompl;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
