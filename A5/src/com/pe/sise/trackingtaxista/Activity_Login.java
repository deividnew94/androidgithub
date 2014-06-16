package com.pe.sise.trackingtaxista;

import greendroid.app.GDActivity;
import com.pe.sise.trackingtaxi.http.Http_ActivityLogin;
import com.pe.sise.trackingtaxista.bean.BeanTaxista;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Login extends GDActivity implements OnClickListener{
	private EditText edtEmail;
	private EditText edtPassword;
	private Button btnIniciarSesion;
	private Button btnRegistrate;
	private TextView txtvOlvideMiPassword;
	
	private String httpResultado;
	private int idHttpResultado;
	public String getHttpResultado() {
		return httpResultado;
		}
	public void setHttpResultado(String psHttpResultado) {
		this.httpResultado = psHttpResultado;
		}
	public void setIdHttpResultado(int idHttpResultado) {
		this.idHttpResultado = idHttpResultado;
		}
	public int getIdHttpResultado() {
		return idHttpResultado;
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.layout_login);
		
		setTitle("Login Taxista");
		edtEmail=(EditText)findViewById(R.id.edtEmail);
		edtPassword=(EditText)findViewById(R.id.edtPassword);
		btnIniciarSesion=(Button)findViewById(R.id.btnIniciarSesion);
		btnIniciarSesion.setOnClickListener(this);
		btnRegistrate=(Button)findViewById(R.id.btnRegistrate);
		btnRegistrate.setOnClickListener(this);
		txtvOlvideMiPassword=(TextView) findViewById(R.id.txtvOlvideMiPassword);
		txtvOlvideMiPassword.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loyout_login, menu);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			Toast.makeText(this, "Este es el menu item 1", Toast.LENGTH_LONG).show();
			break;
		default:
			Toast.makeText(this, "Este es el menu item numero 2", Toast.LENGTH_LONG).show();
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnIniciarSesion:
			if(edtPassword.getText().toString().equals("") || edtEmail.getText().toString().equals(""))
			{
				Toast.makeText(this, "Ingresar Email y Password", Toast.LENGTH_LONG).show();
				 
		        
			}else{
				
				if(edtPassword.getText().toString().equals("") || edtEmail.getText().toString().equals(""))
				{
					Toast.makeText(this, "Ingresar Email y Password", Toast.LENGTH_LONG).show();
					 
			        
				}else{
					BeanTaxista loBeanUsuario=new BeanTaxista();
					loBeanUsuario.setContrasena(edtPassword.getText().toString());
					loBeanUsuario.setEmail(edtEmail.getText().toString());
					Http_ActivityLogin loHttpLogin;
					loHttpLogin = new Http_ActivityLogin(Activity_Login.this, "Enviando al servidor",loBeanUsuario);
					loHttpLogin.execute();
	            	
				}
			}
				break;
				
//				Log.v("msg", "2");
//				BeanTaxista loBeanTaxista = new BeanTaxista();
//				
//				loBeanTaxista.setEmail(edtEmail.getText().toString());
//				loBeanTaxista.setContrasena(edtPassword.getText().toString());
//				
//				DalTaxista loDalTaxista = new DalTaxista(this);
//				
//				BeanTaxista lobeanBeanTaxistaRes = loDalTaxista.subReading(loBeanTaxista);
//				
//				String usuario = BeanMapper.toJson(lobeanBeanTaxistaRes, false);
//				
//				
//				
//				if (lobeanBeanTaxistaRes!=null) 
//				{
//					Log.v("Ok", lobeanBeanTaxistaRes.getNombres());
//					Intent loIntent = new Intent(this, Activity_Inicio.class);
//					loIntent.putExtra("usuario", usuario);
//					startActivity(loIntent);
//				}
//				else
//				{
//					LayoutInflater inflater = getLayoutInflater();
//			        View layout = inflater.inflate(R.layout.toast1,null);
//			        Toast toast = new Toast(getApplicationContext());
//			        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//			        toast.setDuration(Toast.LENGTH_LONG);
//			        toast.setView(layout);
//			        toast.show(); 
//				}
//									
//			}
//			break;
			
		case R.id.btnRegistrate:
			Intent loIntent=new Intent(Activity_Login.this, Activity_Registrate.class);
			loIntent.putExtra("menu", "menu2");
	    	startActivity(loIntent);	    	
	    	break;	
		case R.id.txtvOlvideMiPassword:
			Intent in = new Intent(this,Activity_OlvideMiPassword.class);
			startActivity(in);
			break;
			}
		}
	
	public void subMenu()
	{
		Toast.makeText(this, getHttpResultado(), Toast.LENGTH_LONG).show();
		if(getIdHttpResultado()==1)
		{
			Intent loIntent = new Intent(this, Activity_Inicio.class);
			loIntent.putExtra("NomVariable", "Valor");
			startActivity(loIntent);
		}		
	}
	
	public void suberror()
	{
		Toast loToast = Toast.makeText(this, "error de conexion", Toast.LENGTH_LONG);
		loToast.setGravity(Gravity.CENTER, 0, 0);
		loToast.show();
	}
}
