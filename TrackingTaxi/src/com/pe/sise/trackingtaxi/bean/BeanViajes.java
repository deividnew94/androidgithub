package com.pe.sise.trackingtaxi.bean;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanViajes {
	
	@JsonProperty
	Integer IDVieajes;
	@JsonProperty
	Date Fecha_Inicio;
	@JsonProperty
	String Estado_Viaje;
	@JsonProperty
	Integer ID_Taxista;
	@JsonProperty
	Date Fecha_Fin;
	@JsonProperty
	Integer ID_Cliente;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	public Integer getIDVieajes() {
		return IDVieajes;
	}
	public void setIDVieajes(Integer iDVieajes) {
		IDVieajes = iDVieajes;
	}
	public Date getFecha_Inicio() {
		return Fecha_Inicio;
	}
	public void setFecha_Inicio(Date fecha_Inicio) {
		Fecha_Inicio = fecha_Inicio;
	}
	public String getEstado_Viaje() {
		return Estado_Viaje;
	}
	public void setEstado_Viaje(String estado_Viaje) {
		Estado_Viaje = estado_Viaje;
	}
	public Integer getID_Taxista() {
		return ID_Taxista;
	}
	public void setID_Taxista(Integer iD_Taxista) {
		ID_Taxista = iD_Taxista;
	}
	public Date getFecha_Fin() {
		return Fecha_Fin;
	}
	public void setFecha_Fin(Date fecha_Fin) {
		Fecha_Fin = fecha_Fin;
	}
	public Integer getID_Cliente() {
		return ID_Cliente;
	}
	public void setID_Cliente(Integer iD_Cliente) {
		ID_Cliente = iD_Cliente;
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
