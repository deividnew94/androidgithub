package com.example.utpmatricula;

import com.example.bean.AlumnoBean;
import com.example.dao.AlumnoDAO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Principal extends Activity implements OnClickListener {

	Button btnenviar;
	EditText txtnombre;
	Spinner cbofacultad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		btnenviar=(Button)findViewById(R.id.btnenviar);
		txtnombre=(EditText)findViewById(R.id.txtnombre);
		cbofacultad=(Spinner)findViewById(R.id.cbofacultad);
		btnenviar.setOnClickListener(this);
	}
	
	public void Calcular(){
		int id=(int) cbofacultad.getSelectedItemId();
		Intent loIntent = new Intent(this, Secundario.class);
		loIntent.putExtra("facultad", ""+id);
		loIntent.putExtra("nombre", txtnombre.getText().toString());
		startActivity(loIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btnenviar){
			Calcular();
		}
	}

}
