package br.com.nunes.restaurant.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Embeddable
public class MenuImageCompl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Menu ID")
	@ManyToOne
	@JoinColumn(name="id_menu")
	private Menu menu;

	public MenuImageCompl() {}
	
	public MenuImageCompl(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
