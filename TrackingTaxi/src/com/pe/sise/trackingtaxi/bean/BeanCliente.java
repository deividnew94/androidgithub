package com.pe.sise.trackingtaxi.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanCliente {
	@JsonProperty
	Integer IdCliente;
	@JsonProperty
	String Dni;
	@JsonProperty
	String Nombres;
	@JsonProperty
	String Email;
	@JsonProperty
	String Contrasena;
	@JsonProperty
	String Telefono;
	@JsonProperty
	String Foto;
	@JsonProperty
	String Fecha_Registro;
	@JsonProperty
	String Estado;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	public Integer getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(Integer idCliente) {
		IdCliente = idCliente;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	public String getNombres() {
		return Nombres;
	}
	public void setNombres(String nombres) {
		Nombres = nombres;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getContrasena() {
		return Contrasena;
	}
	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getFoto() {
		return Foto;
	}
	public void setFoto(String foto) {
		Foto = foto;
	}
	public String getFecha_Registro() {
		return Fecha_Registro;
	}
	public void setFecha_Registro(String fecha_Registro) {
		Fecha_Registro = fecha_Registro;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public int getIdResultado() {
		return idResultado;
	}
	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	
	
	
	

}
