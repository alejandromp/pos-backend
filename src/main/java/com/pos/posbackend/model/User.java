package com.pos.posbackend.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Access(AccessType.FIELD)
public class User extends ParentEntity{
	
	private static final long serialVersionUID = 1L;

	@Column(name="usu_usuario", nullable = true, length=40)
	private String usuario;
	
	@Column(name="usu_password", nullable = true, length=15)
	private String password;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
