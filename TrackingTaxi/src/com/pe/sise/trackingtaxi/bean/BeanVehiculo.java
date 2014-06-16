package com.pe.sise.trackingtaxi.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanVehiculo {
	
	@JsonProperty
	Integer IdVehiculo;
	@JsonProperty
	String Fabricante;
	@JsonProperty
	String Placa;
	@JsonProperty
	String Numero_Registro;
	@JsonProperty
	String Modelo;
	@JsonProperty
	Float Costo_Hora;
	@JsonProperty
	String Foto;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String resultado="";
	
	
	public Integer getIdVehiculo() {
		return IdVehiculo;
	}
	public void setIdVehiculo(Integer idVehiculo) {
		IdVehiculo = idVehiculo;
	}
	public String getFabricante() {
		return Fabricante;
	}
	public void setFabricante(String fabricante) {
		Fabricante = fabricante;
	}
	public String getPlaca() {
		return Placa;
	}
	public void setPlaca(String placa) {
		Placa = placa;
	}
	public String getNumero_Registro() {
		return Numero_Registro;
	}
	public void setNumero_Registro(String numero_Registro) {
		Numero_Registro = numero_Registro;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	public Float getCosto_Hora() {
		return Costo_Hora;
	}
	public void setCosto_Hora(Float costo_Hora) {
		Costo_Hora = costo_Hora;
	}
	public String getFoto() {
		return Foto;
	}
	public void setFoto(String foto) {
		Foto = foto;
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
