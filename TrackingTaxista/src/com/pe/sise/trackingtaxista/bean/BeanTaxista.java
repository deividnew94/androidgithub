package com.pe.sise.trackingtaxista.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanTaxista {
	
	@JsonProperty
	Integer idTaxista;
	@JsonProperty
	String Nombres;
	@JsonProperty
	String Apellidos;
	@JsonProperty
	String Usuario;
	@JsonProperty
	String Password;
	@JsonProperty
	String email;
	@JsonProperty
	Integer VehiculoId;
	
	public Integer getIdTaxista() {
		return idTaxista;
	}
	public void setIdTaxista(Integer idTaxista) {
		this.idTaxista = idTaxista;
	}
	public String getNombres() {
		return Nombres;
	}
	public void setNombres(String nombres) {
		Nombres = nombres;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getVehiculoId() {
		return VehiculoId;
	}
	public void setVehiculoId(Integer vehiculoId) {
		VehiculoId = vehiculoId;
	}
	
	
}
