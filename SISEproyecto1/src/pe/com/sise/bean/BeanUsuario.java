package pe.com.sise.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties ;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanUsuario {

	@JsonProperty
    private long Cantidad=0;
	@JsonProperty
    private String cantidad="";
	@JsonProperty
    private String codSincronizacion="";
	@JsonProperty
    private String clave="";
	@JsonProperty
    private String flgPermisoFoto="";
	@JsonProperty
    private String flgPermisoGps="";
	@JsonProperty
    private String flgPermisoMotivo="";
	@JsonProperty
    private long id=0;
	@JsonProperty
    private int idResultado=0;
	@JsonProperty
    private String login="";
	@JsonProperty
    private String nombre="";
	@JsonProperty
    private String resultado="";
	
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodSincronizacion() {
		return codSincronizacion;
	}
	public void setCodSincronizacion(String codSincronizacion) {
		this.codSincronizacion = codSincronizacion;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getFlgPermisoFoto() {
		return flgPermisoFoto;
	}
	public void setFlgPermisoFoto(String flgPermisoFoto) {
		this.flgPermisoFoto = flgPermisoFoto;
	}
	public String getFlgPermisoGps() {
		return flgPermisoGps;
	}
	public void setFlgPermisoGps(String flgPermisoGps) {
		this.flgPermisoGps = flgPermisoGps;
	}
	public String getFlgPermisoMotivo() {
		return flgPermisoMotivo;
	}
	public void setFlgPermisoMotivo(String flgPermisoMotivo) {
		this.flgPermisoMotivo = flgPermisoMotivo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIdResultado() {
		return idResultado;
	}
	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public double getSequenciaTransaccion() {
		return sequenciaTransaccion;
	}
	public void setSequenciaTransaccion(double sequenciaTransaccion) {
		this.sequenciaTransaccion = sequenciaTransaccion;
	}
	@JsonProperty
    private double sequenciaTransaccion=0;
    	
}
