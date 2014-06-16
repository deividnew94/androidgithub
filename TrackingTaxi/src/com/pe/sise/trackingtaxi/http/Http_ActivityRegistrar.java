package com.pe.sise.trackingtaxi.http;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.pe.sise.trackingtaxi.Activity_Registrate;
import com.pe.sise.trackingtaxi.bean.BeanCliente;
import com.pe.sise.trackingtaxi.bean.BeanMapper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

public class Http_ActivityRegistrar extends AsyncTask<Void, Void, String>{
	public Context ioContext;
	private ProgressDialog ioProgressDialog;
	private String isMensaje;
	private BeanCliente ioBeanCliente;
	
	public Http_ActivityRegistrar(Context psClase,String psMensaje, BeanCliente poBeanCliente)
	{
		ioContext=psClase;
		isMensaje=psMensaje;
		ioBeanCliente=poBeanCliente;
	}

	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return fnRegistarte();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if (ioProgressDialog != null) {
			ioProgressDialog.dismiss();
			((Activity_Registrate)ioContext).subMenu();
			}
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		ioProgressDialog = ProgressDialog.show(ioContext, "",isMensaje, true);
		//((Activity)ioContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		ioProgressDialog.setMessage(Html.fromHtml("<font color='white'>" + isMensaje + "</font>"));
	}
	
	private String fnRegistarte() {
		try {
			//INICIALIZACION
			RestTemplate loRestTemplate = new RestTemplate();
			loRestTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
			loRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			
			//Conexion
			String lscadena=loRestTemplate.postForObject("http://190.43.255.177/Taxi/Login.ashx",ioBeanCliente, String.class);
			Log.v("URL", "http://190.43.255.177/Taxi/Login.ashx");
			Log.v("URL", BeanMapper.toJson(ioBeanCliente, false));
			Log.v("URL", lscadena);
			
			BeanCliente loBeanCliente=(BeanCliente)BeanMapper. fromJson(lscadena, BeanCliente.class );
			((Activity_Registrate)ioContext).setHttpResultado(loBeanCliente.getResultado());
			((Activity_Registrate)ioContext).setIdHttpResultado(loBeanCliente.getIdResultado());
		} catch (Exception e) {
			// TODO: handle exception
			((Activity_Registrate)ioContext).suberror();
			return null;
		}
		return null;
	}

}
