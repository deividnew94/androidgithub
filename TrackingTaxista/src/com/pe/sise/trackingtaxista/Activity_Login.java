package com.pe.sise.trackingtaxista;

import greendroid.app.GDActivity;

import com.pe.sise.trackingtaxista.bean.BeanMapper;
import com.pe.sise.trackingtaxista.bean.BeanTaxista;
import com.pe.sise.trackingtaxista.dal.DalTaxista;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Login extends GDActivity implements OnClickListener{
	private EditText edtUsuario;
	private EditText edtPassword;
	private Button btnIniciarSesion;
	private Button btnRegistrate;
	private TextView txtvOlvideMiPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.layout_login);
		
		setTitle("Login Taxista");
		edtUsuario=(EditText)findViewById(R.id.edtUsuario);
		edtUsuario.setText("caristi4");
		edtPassword=(EditText)findViewById(R.id.edtPassword);
		edtPassword.setText("123");
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
			if(edtPassword.getText().toString().equals("") || edtUsuario.getText().toString().equals(""))
			{
				Toast.makeText(this, "Ingresar Usuario y Password", Toast.LENGTH_LONG).show();
				 
		        
			}else{
				Log.v("msg", "2");
				BeanTaxista loBeanTaxista = new BeanTaxista();
				
				loBeanTaxista.setUsuario(edtUsuario.getText().toString());
				loBeanTaxista.setPassword(edtPassword.getText().toString());
				
				DalTaxista loDalTaxista = new DalTaxista(this);
				
				BeanTaxista lobeanBeanTaxistaRes = loDalTaxista.subReading(loBeanTaxista);
				
				String usuario = BeanMapper.toJson(lobeanBeanTaxistaRes, false);
				
				
				
				if (lobeanBeanTaxistaRes!=null) 
				{
					Log.v("Ok", lobeanBeanTaxistaRes.getNombres());
					Intent loIntent = new Intent(this, Activity_Inicio.class);
					loIntent.putExtra("usuario", usuario);
					startActivity(loIntent);
				}
				else
				{
					LayoutInflater inflater = getLayoutInflater();
			        View layout = inflater.inflate(R.layout.toast1,null);
			        Toast toast = new Toast(getApplicationContext());
			        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			        toast.setDuration(Toast.LENGTH_LONG);
			        toast.setView(layout);
			        toast.show(); 
				}
									
			}
			break;
			
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
}
