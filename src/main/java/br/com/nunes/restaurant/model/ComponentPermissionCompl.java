package br.com.nunes.restaurant.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Embeddable
public class ComponentPermissionCompl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "User ID")
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	
	@ApiModelProperty(value = "Component ID")
	@ManyToOne
	@JoinColumn(name="component")
	private Component component;
	
	public ComponentPermissionCompl() {}

	public ComponentPermissionCompl(User user, Component component) {
		this.user = user;
		this.component = component;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}	

}
