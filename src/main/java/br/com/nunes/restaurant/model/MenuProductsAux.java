package br.com.nunes.restaurant.model;

import java.util.List;

import javax.validation.constraints.NotNull;

public class MenuProductsAux {
	
	@NotNull(message = "Required 'id_menu' field.")
	private Long id_menu;
	
	private List<MenuProductsAuxProducts> products;

	public Long getId_menu() {
		return id_menu;
	}

	public void setId_menu(Long id_menu) {
		this.id_menu = id_menu;
	}

	public List<MenuProductsAuxProducts> getProducts() {
		return products;
	}

	public void setProducts(List<MenuProductsAuxProducts> products) {
		this.products = products;
	}

}
