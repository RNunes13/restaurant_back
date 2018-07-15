package br.com.nunes.restaurant.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "menu_products")
@ApiModel(value = "Menu Products")
public class MenuProducts {
	
	@EmbeddedId
	private MenuProductsCompl menuProductsCompl;

	public MenuProductsCompl getMenuProductsCompl() {
		return menuProductsCompl;
	}

	public void setMenuProductsCompl(MenuProductsCompl menuProductsCompl) {
		this.menuProductsCompl = menuProductsCompl;
	}	

}
