package com.pe.sise.trackingtaxi.http;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.pe.sise.trackingtaxista.Activity_Login;
import com.pe.sise.trackingtaxista.bean.BeanMapper;
import com.pe.sise.trackingtaxista.bean.BeanTaxista;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

public class Http_ActivityLogin extends AsyncTask<Void, Void, String> {
	
	public Context ioContext;
	private ProgressDialog ioProgressDialog;
	private String isMensaje;
	private BeanTaxista ioBeanTaxista;
	
	public Http_ActivityLogin(Context psClase,String psMensaje, BeanTaxista poBeanCliente)
	{
		ioContext=psClase;
		isMensaje=psMensaje;
		ioBeanTaxista=poBeanCliente;
	}

	@Override
	protected String doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		return fnConectar();
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		//super.onPostExecute(result);
		if (ioProgressDialog != null) {
			ioProgressDialog.dismiss();
			((Activity_Login)ioContext).subMenu();
			}
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		ioProgressDialog = ProgressDialog.show(ioContext, "",isMensaje, true);
		//((Activity)ioContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		ioProgressDialog.setMessage(Html.fromHtml("<font color='white'>" + isMensaje + "</font>"));
	}

	private String fnConectar() {
		
		try {
			//INICIALIZACION
			RestTemplate loRestTemplate = new RestTemplate();
			loRestTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
			loRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			
			//Conexion
			String lscadena=loRestTemplate.postForObject("http://190.43.255.177/Taxi/Login.ashx",ioBeanTaxista, String.class);
			Log.v("URL", "http://190.43.255.177/Taxi/Login.ashx");
			Log.v("URL", BeanMapper.toJson(ioBeanTaxista, false));
			Log.v("URL", lscadena);
			
			BeanTaxista loBeanTaxista=(BeanTaxista)BeanMapper. fromJson(lscadena, BeanTaxista.class );
			if (loBeanTaxista!=null) {
				((Activity_Login)ioContext).setHttpResultado("OK");
				((Activity_Login)ioContext).setIdHttpResultado(1);	
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			((Activity_Login)ioContext).suberror();
			return null;
		}
		return null;
	}
	

}
