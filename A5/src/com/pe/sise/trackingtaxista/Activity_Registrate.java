package com.pe.sise.trackingtaxista;

import greendroid.app.GDActivity;

import java.util.List;

import com.pe.sise.trackingtaxi.http.Http_ActivityRegistrar;
import com.pe.sise.trackingtaxista.bean.BeanTaxista;
import com.pe.sise.trackingtaxista.bean.SpinnerObject;
import com.pe.sise.trackingtaxista.dal.DalTaxista;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_Registrate extends GDActivity implements OnClickListener{
	
	private EditText edtDni;
	private EditText edtNombres;
	private EditText edtEmail;
	private EditText edtPass;
	private EditText edtTelefono;
	private EditText edtDireccion;
	private Spinner spnDistrito;
	private EditText edtFoto;
	private Button btnCargar;
	private Spinner spnVehiculo;
	private Button btnRegistrar;
	
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
	

    public static Context context;
	
	DalTaxista loDalTaxista = new DalTaxista(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.layout_registrate);
		
		edtDni=(EditText)findViewById(R.id.edtDni);
		edtNombres=(EditText)findViewById(R.id.edtNombres);
		edtEmail=(EditText)findViewById(R.id.edtEmail);
		edtPass=(EditText)findViewById(R.id.edtPass);
		edtTelefono=(EditText)findViewById(R.id.edtTelefono);
		edtDireccion=(EditText)findViewById(R.id.edtDireccion);
		spnDistrito=(Spinner)findViewById(R.id.spnDistrito);
		loadSpinnerDistrito();
		edtFoto=(EditText)findViewById(R.id.edtFoto);
		btnCargar=(Button)findViewById(R.id.btnCargar);
		btnCargar.setOnClickListener(this);
		spnVehiculo=(Spinner)findViewById(R.id.spnVehiculo);
		loadSpinnerVehiculo();	
		btnRegistrar=(Button)findViewById(R.id.btnRegistrarTaxista);
		btnRegistrar.setOnClickListener(this);
		
	}
	
	private void loadSpinnerVehiculo() {	    
	    // Spinner Drop down elements
	    List<SpinnerObject> lables = loDalTaxista.getAllLabels();
	    // Creating adapter for spinner
	    ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
	    android.R.layout.simple_spinner_item, lables);
	    // Drop down layout style - list view with radio button
	    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // attaching data adapter to spinner
	    spnVehiculo.setAdapter(dataAdapter);
	}
	
	private void loadSpinnerDistrito() {	    
	    // Spinner Drop down elements
	    List<SpinnerObject> lables = loDalTaxista.getAllLabelDistrito();
	    // Creating adapter for spinner
	    ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
	    android.R.layout.simple_spinner_item, lables);
	    // Drop down layout style - list view with radio button
	    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // attaching data adapter to spinner
	    spnDistrito.setAdapter(dataAdapter);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loyout_registrate, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegistrarTaxista:
//			if (edtDni.getText().toString().equals("") && edtNombres.getText().toString().equals("") && edtEmail.getText().toString().equals("") && edtPass.getText().toString().equals("") && edtPass.getText().toString().equals("") && edtTelefono.getText().toString().equals("") && edtDireccion.getText().toString().equals("") && edtDireccion.getText().toString().equals("") && edtFoto.getText().toString().equals("")) 
//			{
//				Toast.makeText(this, "Ingresar todos los Registros", Toast.LENGTH_LONG).show();
//			}
//			else
//			{
			BeanTaxista beanTaxista = new BeanTaxista();			
			beanTaxista.setDni(edtDni.getText().toString());
			beanTaxista.setNombre(edtNombres.getText().toString());
			beanTaxista.setEmail(edtEmail.getText().toString());
			beanTaxista.setContrasena(edtPass.getText().toString());
			beanTaxista.setTelefono(edtTelefono.getText().toString());
			beanTaxista.setDireccion(edtDireccion.getText().toString());
			beanTaxista.setID_Distrito(((SpinnerObject)spnDistrito.getSelectedItem() ).getId().toString());
			//beanTaxista.setFoto(edtFoto.getText().toString());
			beanTaxista.setTelefono(edtTelefono.getText().toString());			
			beanTaxista.setID_Vehiculo(((SpinnerObject)spnVehiculo.getSelectedItem()).getId().toString());
			
			Toast.makeText(this, ((SpinnerObject)spnDistrito.getSelectedItem()).getId().toString(), Toast.LENGTH_LONG).show();
			
//			Boolean rpta = loDalTaxista.subInsert(beanTaxista);
//			Http_ActivityRegistrar loHttpLogin;
//			loHttpLogin = new Http_ActivityRegistrar(Activity_Registrate.this, "Enviando al servidor",beanTaxista);
//			loHttpLogin.execute();
			
//			Boolean rpta=true;
//			
//			if (rpta) {
//				Toast loToast = Toast.makeText(this, "USUARIO REGISTRADO CORRECTAMENTE", Toast.LENGTH_LONG);
//				loToast.setGravity(Gravity.CENTER, 0, 0);
//				loToast.show();
//				Intent loIntent=new Intent(this, Activity_Login.class);				
//		    	startActivity(loIntent);
//			}
//			else { Toast.makeText(this, "ERROR EN EL REGISTRO", Toast.LENGTH_LONG).show();}
//			
//			break;			
			}
		//}
	}
	
	public void subMenu()
	{
		Toast.makeText(this, getHttpResultado(), Toast.LENGTH_LONG).show();
		if(getIdHttpResultado()==1)
		{
			Intent loIntent = new Intent(this, Activity_Login.class);
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
