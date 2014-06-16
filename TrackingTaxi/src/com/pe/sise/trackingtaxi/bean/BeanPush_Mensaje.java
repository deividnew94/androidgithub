package com.pe.sise.trackingtaxi.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanPush_Mensaje {
	
	@JsonProperty
	Integer IdPush_Mensaje;
	@JsonProperty
	String Mensaje;
	@JsonProperty
	Integer ID_Taxista;
	@JsonProperty
	Integer ID_Cliente;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	public Integer getIdPush_Mensaje() {
		return IdPush_Mensaje;
	}
	public void setIdPush_Mensaje(Integer idPush_Mensaje) {
		IdPush_Mensaje = idPush_Mensaje;
	}
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	public Integer getID_Taxista() {
		return ID_Taxista;
	}
	public void setID_Taxista(Integer iD_Taxista) {
		ID_Taxista = iD_Taxista;
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
