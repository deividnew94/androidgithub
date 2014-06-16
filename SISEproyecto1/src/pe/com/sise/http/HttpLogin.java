package pe.com.sise.http;


import pe.com.sise.bean.*;
import pe.com.sise.ActLogin;
import pe.com.sise.ActMenu;
import pe.com.sise.ActMenu2;
import pe.com.sise.ActSplash;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class HttpLogin extends AsyncTask<Void, Void, String> {
	public Context ioContext;
	private ProgressDialog ioProgressDialog;
	private String isMensaje;
	private BeanUsuario ioBeanUsuario;
	
	public HttpLogin(Context psClase,String psMensaje,BeanUsuario poBeanUsuario)
	{
		ioContext=psClase;
		isMensaje=psMensaje;
		ioBeanUsuario=poBeanUsuario;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		return fnConectar();
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		//super.onPostExecute(result);
		if (ioProgressDialog != null) {
			ioProgressDialog.dismiss();
			((ActLogin)ioContext).subMenu();
			//Intent loIntent = new Intent(ioContext, ActMenu2.class);
			//ioContext.startActivity(loIntent);
			
		}
		
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		ioProgressDialog = ProgressDialog.show(ioContext, "",isMensaje, true);
		//((Activity)ioContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		ioProgressDialog.setMessage(Html.fromHtml("<font color='white'>" + isMensaje + "</font>"));
	}

	private String  fnConectar()
	{
		
		try {

			//INICIALIZACION
			RestTemplate loRestTemplate = new RestTemplate();
			loRestTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
			loRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			
			//Conexion
			String lscadena=loRestTemplate.postForObject("http://demosdata.nextel.com.pe/demo_nactivity_mp/mobile/CargaAndroid/Login.ashx",ioBeanUsuario,String.class);
			Log.v("URL", "http://demosdata.nextel.com.pe/demo_nactivity_mp/mobile/CargaAndroid/Login.ashx");
			Log.v("URL", BeanMapper.toJson(ioBeanUsuario, false));
			Log.v("URL", lscadena);
			
			BeanUsuario lobeanUsuario=(BeanUsuario)BeanMapper. fromJson(lscadena, BeanUsuario.class );
			((ActLogin)ioContext).setHttpResultado(lobeanUsuario.getResultado());
			((ActLogin)ioContext).setIdHttpResultado(lobeanUsuario.getIdResultado());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			((ActLogin)ioContext).suberror();
			return null;
		}
        
		return null;
	}
}
