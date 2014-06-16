package com.pe.sise.trackingtaxista.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanTaxista {
	
	@JsonProperty
	String ID_Taxista;
	@JsonProperty
	String Dni;
	@JsonProperty
	String Nombre;
	@JsonProperty
	String Email;
	@JsonProperty
	String Contrasena;
	@JsonProperty
	String Telefono;
	@JsonProperty
	String Direccion;
	@JsonProperty
	String ID_Distrito;
	@JsonProperty
	String Foto;
	@JsonProperty
	String Fecha_Registro;
	@JsonProperty
	String ID_Vehiculo;
	@JsonProperty
	String Estado;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	public String getID_Taxista() {
		return ID_Taxista;
	}
	public void setID_Taxista(String ID_Taxista) {
		this.ID_Taxista = ID_Taxista;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
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
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getID_Distrito() {
		return ID_Distrito;
	}
	public void setID_Distrito(String iD_Distrito) {
		ID_Distrito = iD_Distrito;
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
	public String getID_Vehiculo() {
		return ID_Vehiculo;
	}
	public void setID_Vehiculo(String iD_Vehiculo) {
		ID_Vehiculo = iD_Vehiculo;
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
