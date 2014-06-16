package com.pe.sise.trackingtaxi.http;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.pe.sise.trackingtaxista.Activity_Registrate;
import com.pe.sise.trackingtaxista.bean.BeanMapper;
import com.pe.sise.trackingtaxista.bean.BeanTaxista;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

public class Http_ActivityRegistrar extends AsyncTask<Void, Void, String>{
	public Context ioContext;
	private ProgressDialog ioProgressDialog;
	private String isMensaje;
	private BeanTaxista ioBeanTaxista;
	
	public Http_ActivityRegistrar(Context psClase,String psMensaje, BeanTaxista poBeanTaxista)
	{
		ioContext=psClase;
		isMensaje=psMensaje;
		ioBeanTaxista=poBeanTaxista;
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
			String lscadena=loRestTemplate.postForObject("http://190.43.255.177/Taxi/RegistrarTaxista.ashx",ioBeanTaxista, String.class);
			Log.v("URL", "http://10.0.2.2:1348/Login.ashx");
			Log.v("URL", BeanMapper.toJson(ioBeanTaxista, false));
			Log.v("URL", lscadena);			
			Boolean rpta=false;
			if (lscadena.equals("true")) {
				rpta=true;
			}
			//BeanTaxista loBeanTaxista=(BeanTaxista)BeanMapper. fromJson(lscadena, BeanTaxista.class );
			if (rpta) {
				((Activity_Registrate)ioContext).setHttpResultado("OK");
				((Activity_Registrate)ioContext).setIdHttpResultado(1);	
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			((Activity_Registrate)ioContext).suberror();
			return null;
		}
		return null;
	}

}
