package com.pe.sise.trackingtaxista;

import greendroid.app.GDActivity;

import java.util.List;

import com.pe.sise.trackingtaxista.bean.BeanTaxista;
import com.pe.sise.trackingtaxista.bean.SpinnerObject;
import com.pe.sise.trackingtaxista.dal.DalTaxista;

import android.os.Bundle;
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
	
	private EditText edtNombres;
	private EditText edtApellidos;
	private EditText edtUsuario;
	private EditText edtPassword;
	private EditText edtEmail;
	private Button btnRegistrar;
	private Spinner spnVehiculo;
	
	DalTaxista loDalTaxista = new DalTaxista(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.layout_registrate);
		
		btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
		btnRegistrar.setOnClickListener(this);
		edtNombres=(EditText)findViewById(R.id.edtNombres);
		//edtNombres.setText("123");
		edtApellidos=(EditText)findViewById(R.id.edtApellidos);
		//edtApellidos.setText("123");
		edtUsuario=(EditText)findViewById(R.id.edtUser);
		//edtUsuario.setText("123");
		edtPassword=(EditText)findViewById(R.id.edtPass);
		//edtPassword.setText("123");
		edtEmail=(EditText)findViewById(R.id.edtEmail);
		//edtEmail.setText("123");
		spnVehiculo=(Spinner)findViewById(R.id.spnVehiculo);
		loadSpinnerVehiculo();						
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loyout_registrate, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegistrar:
			if(edtNombres.getText().toString().equals("") && edtApellidos.getText().toString().equals("") && edtUsuario.getText().toString().equals("") && edtPassword.getText().toString().equals("") && edtEmail.getText().toString().equals(""))
			{
				Toast.makeText(this, "Ingresar todos los Registros", Toast.LENGTH_LONG).show();
			}
			else
			{
			BeanTaxista beanTaxista = new BeanTaxista();			
			beanTaxista.setNombres(edtNombres.getText().toString());
			beanTaxista.setApellidos(edtApellidos.getText().toString());
			beanTaxista.setUsuario(edtUsuario.getText().toString());
			beanTaxista.setPassword(edtPassword.getText().toString());
			beanTaxista.setEmail(edtEmail.getText().toString());
			beanTaxista.setVehiculoId(Integer.parseInt(((SpinnerObject)spnVehiculo.getSelectedItem()).getId()));
			
			Boolean rpta = loDalTaxista.subInsert(beanTaxista);
			if (rpta) {
				Toast loToast = Toast.makeText(this, "USUARIO REGISTRADO CORRECTAMENTE", Toast.LENGTH_LONG);
				loToast.setGravity(Gravity.CENTER, 0, 0);
				loToast.show();
				Intent loIntent=new Intent(this, Activity_Login.class);				
		    	startActivity(loIntent);
			}
			else { Toast.makeText(this, "ERROR EN EL REGISTRO", Toast.LENGTH_LONG).show();}
			
			break;			
			}
		}
	}
	
}
