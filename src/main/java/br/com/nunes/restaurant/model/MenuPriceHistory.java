package br.com.nunes.restaurant.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "menu_price_history")
@ApiModel(value = "Menu Price History")
public class MenuPriceHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "ID")
	@Column(name = "id", nullable = false, updatable = false, length = 10)
	private Long id;
	
	@ApiModelProperty(value = "Menu ID")
	@ManyToOne
	@JoinColumn(name="menu")
	private Menu menu;
	
	@ApiModelProperty(value = "Old Price")
	@Column(name = "old_price", nullable = false, precision = 10, scale = 3)
	@DecimalMax("999999999.0")
	private BigDecimal oldPrice;
	
	@ApiModelProperty(value = "New Price")
	@Column(name = "new_price", nullable = false, precision = 10, scale = 3)
	@DecimalMax("999999999.0")
	private BigDecimal newPrice;
	
	@CreationTimestamp
	@ApiModelProperty(value = "Change date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "changedAt" , updatable = false, nullable = false)
	private Calendar changedAt;
	
	public MenuPriceHistory() {}

	public MenuPriceHistory(Menu menu, BigDecimal oldPrice, BigDecimal newPrice) {
		this.menu = menu;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	public BigDecimal getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}

	public Calendar getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Calendar changedAt) {
		this.changedAt = changedAt;
	}

}
