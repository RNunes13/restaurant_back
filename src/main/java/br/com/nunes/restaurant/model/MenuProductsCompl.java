package br.com.nunes.restaurant.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Embeddable
public class MenuProductsCompl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Menu ID")
	@ManyToOne
	@JoinColumn(name="id_menu")
	private Menu menu;
	
	@ApiModelProperty(value = "Product ID")
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;
	
	public MenuProductsCompl() {}

	public MenuProductsCompl(Menu menu, Product product) {
		this.menu = menu;
		this.product = product;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
