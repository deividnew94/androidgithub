package com.pe.sise.trackingtaxi.bean;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanEstado {
	
	@JsonProperty
	Integer IdEstado;
	@JsonProperty
	String Descripcion;
	@JsonProperty
	String Latitud;
	@JsonProperty
	String Longitud;
	@JsonProperty
	Date Hora_Fecha;
	@JsonProperty
	Integer ID_Viaje;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	public Integer getIdEstado() {
		return IdEstado;
	}
	public void setIdEstado(Integer idEstado) {
		IdEstado = idEstado;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getLatitud() {
		return Latitud;
	}
	public void setLatitud(String latitud) {
		Latitud = latitud;
	}
	public String getLongitud() {
		return Longitud;
	}
	public void setLongitud(String longitud) {
		Longitud = longitud;
	}
	public Date getHora_Fecha() {
		return Hora_Fecha;
	}
	public void setHora_Fecha(Date hora_Fecha) {
		Hora_Fecha = hora_Fecha;
	}
	public Integer getID_Viaje() {
		return ID_Viaje;
	}
	public void setID_Viaje(Integer iD_Viaje) {
		ID_Viaje = iD_Viaje;
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
