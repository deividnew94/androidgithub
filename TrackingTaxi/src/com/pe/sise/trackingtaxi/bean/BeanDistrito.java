package com.pe.sise.trackingtaxi.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanDistrito {
	
	@JsonProperty
	Integer IdDistrito;
	@JsonProperty
	String Nombre_Distrito;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	public Integer getIdDistrito() {
		return IdDistrito;
	}
	public void setIdDistrito(Integer idDistrito) {
		IdDistrito = idDistrito;
	}
	public String getNombre_Distrito() {
		return Nombre_Distrito;
	}
	public void setNombre_Distrito(String nombre_Distrito) {
		Nombre_Distrito = nombre_Distrito;
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
