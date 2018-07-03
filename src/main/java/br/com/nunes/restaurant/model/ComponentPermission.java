package br.com.nunes.restaurant.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "component_permission")
@ApiModel(value = "Component Permission")
public class ComponentPermission {
	
	@EmbeddedId
	private ComponentPermissionCompl componentPermissionCompl;

	@ApiModelProperty(value = "Permission (1 - Read, 2 - Read/Create, 3 - Read/Create/Update, 4 - Read/Create/Update/Delete)")
	@Column(name = "permission", nullable = false, length = 1)
	@NotNull(message = "Required 'permission' field.")
	private Long permission;

	public ComponentPermissionCompl getComponentPermissionCompl() {
		return componentPermissionCompl;
	}

	public void setComponentPermissionCompl(ComponentPermissionCompl componentPermissionCompl) {
		this.componentPermissionCompl = componentPermissionCompl;
	}

	public Long getPermission() {
		return permission;
	}

	public void setPermission(Long permission) {
		this.permission = permission;
	}

}
