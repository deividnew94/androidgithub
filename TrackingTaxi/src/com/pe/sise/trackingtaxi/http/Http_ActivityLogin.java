package com.pe.sise.trackingtaxi.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.pe.sise.trackingtaxi.Activity_Login;
import com.pe.sise.trackingtaxi.bean.BeanMapper;
import com.pe.sise.trackingtaxi.bean.BeanTaxista;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

public class Http_ActivityLogin extends AsyncTask<Void, Void, String> {
	
	//ESTAS VARIABLES SIEMPRE VAN
	public Context ioContext;
	private ProgressDialog ioProgressDialog;//BARRA DE PROGRESO
	private String isMensaje;//NOMBRE DEL MENSAJE BARRA DE PROGRESO
	private BeanTaxista ioBeanTaxista;//ESTE BEAN TIENE Q CAMBIAR X EL Q QUIERES PASAR COMO PARAMETRO PARA EL METODO HTTP
	///////////////////////////////////////////////////////
	//EN EL CONSTRUCTOR AGREGA LAS VARIABLES DEL ACTIVITY Q LO LLAMO A SUS VARIABLES LOCALES

	//JSONParser jsonParser = new JSONParser();
	// url to create new product
	private static String url_create_product = "http://misitiodemostracion.site90.net/gcm_server_php/loguin.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	
	public Http_ActivityLogin(Context psClase,String psMensaje, BeanTaxista poBeanTaxista)
	{
		ioContext=psClase;
		isMensaje=psMensaje;
		ioBeanTaxista=poBeanTaxista;
	}
	/////////////////////////////////////////////////
	//MIENTRAS Q SE EJECUTA
	@Override
	protected String doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		return fnConectar();
	}
	//////////////////////////
	//ANTES DE TERMINARSE
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		//super.onPostExecute(result);
		if (ioProgressDialog != null) {
			ioProgressDialog.dismiss();
			((Activity_Login)ioContext).subMenu();//SI ENCONTRO USUARIO INGRESADO EN LA BD PASA AL SIGUIENTE ACTIVITY
			}
	}
	/////////////////////////////////////////
	//EN PRE EJECUCION
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		ioProgressDialog = ProgressDialog.show(ioContext, "",isMensaje, true);
		//((Activity)ioContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		ioProgressDialog.setMessage(Html.fromHtml("<font color='white'>" + isMensaje + "</font>"));
	}
	/////////////////////////////////////////////////	
	//METODO QUE RECIBE BEAN , TRANSFORMA A JSON , ENVIA JSON , RETORNA UNA CADENA JSON , TRANSFORMA CADENA JSON A BEAN 
	private String fnConectar() {
		
		try {
			//INICIALIZACION NO LO TOQUES
			RestTemplate loRestTemplate = new RestTemplate();
			loRestTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
			loRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			
//			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
//					"POST", ioBeanTaxista);
			
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("email", ioBeanTaxista.getEmail()));
			params.add(new BasicNameValuePair("contrasena", ioBeanTaxista.getContrasena()));
			
			// getting JSON Object
			// Note that create product url accepts POST method
			//JSONObject json = jsonParser.makeHttpRequest(url_create_product,
			//		"POST", params);
			
			// check log cat fro response
			//Log.d("Create Response", json.toString());
			//int success = json.getInt(TAG_SUCCESS);
			
			/*//Conexion
			String urlconexion=url_create_product;//ESTA URL DEJALA COMO ESTA PAMPA SE ENCARGARA DE CAMBIARLA PARA EL TESTEO
			String lscadena=loRestTemplate.postForObject(urlconexion,ioBeanTaxista, String.class);//ENVIA BEAN RECIBE CADENA JSON
			
			//ESTO ES PARA IMPRIMIR
			Log.v("URL", url_create_product);
			Log.v("URL", BeanMapper.toJson(ioBeanTaxista, false));
			Log.v("URL", lscadena);
			*/
			BeanTaxista loBeanTaxista = new BeanTaxista();
//			try {
//
//				//loBeanTaxista=(BeanTaxista)BeanMapper.fromJson(json.toString(), BeanTaxista.class );//RECIBE CADENA JSON RETORNA BEAN X TIPO DE PARAMETRO BEAN
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
			//loBeanTaxista.setIdResultado(success);
			//loBeanTaxista.setResultado(TAG_SUCCESS);
			//ESTO SE ACTUALIZA EN EL ACTIVITY Q LO LLAMO
			//((Activity_Login)ioContext).setHttpResultado(loBeanTaxista.getResultado());
			//((Activity_Login)ioContext).setIdHttpResultado(loBeanTaxista.getIdResultado());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//ESTO SE ACTUALIZA EN EL ACTIVITY Q LO LLAMO
			((Activity_Login)ioContext).suberror();
			return null;
		}
		return null;
	}
	/////////////////////////////////////////////////

}
