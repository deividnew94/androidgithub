package com.pe.sise.trackingtaxi.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.android.gms.maps.model.LatLng;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanMarkers {
	@JsonProperty
	private String fname;
	@JsonProperty
	private String lname;
	@JsonProperty
	private String uname;
	@JsonProperty
	private String gcm_regit;
	@JsonProperty
	private String estado;
	@JsonProperty
	private LatLng latlng;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getGcm_regit() {
		return gcm_regit;
	}
	public void setGcm_regit(String gcm_regit) {
		this.gcm_regit = gcm_regit;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LatLng getLatlng() {
		return latlng;
	}
	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}
}
