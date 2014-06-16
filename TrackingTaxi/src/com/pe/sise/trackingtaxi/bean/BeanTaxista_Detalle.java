package com.pe.sise.trackingtaxi.bean;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanTaxista_Detalle {
	
	@JsonProperty
	Integer IdTaxista_Detalle;
	@JsonProperty
	String Latitud;
	@JsonProperty
	String Longitud;
	@JsonProperty
	Boolean Estado;
	@JsonProperty
	Date Hora_Fecha;
	@JsonProperty
	Integer ID_Taxista;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	public Integer getIdTaxista_Detalle() {
		return IdTaxista_Detalle;
	}
	public void setIdTaxista_Detalle(Integer idTaxista_Detalle) {
		IdTaxista_Detalle = idTaxista_Detalle;
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
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	public Date getHora_Fecha() {
		return Hora_Fecha;
	}
	public void setHora_Fecha(Date hora_Fecha) {
		Hora_Fecha = hora_Fecha;
	}
	public Integer getID_Taxista() {
		return ID_Taxista;
	}
	public void setID_Taxista(Integer iD_Taxista) {
		ID_Taxista = iD_Taxista;
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
