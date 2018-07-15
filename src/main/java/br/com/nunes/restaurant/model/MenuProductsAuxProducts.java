package br.com.nunes.restaurant.model;

import javax.validation.constraints.NotNull;

public class MenuProductsAuxProducts {
	
	@NotNull(message = "Required 'id_product' field.")
	private Long id_product;

	public Long getId_product() {
		return id_product;
	}

	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}

}
