package com.example.utpmatricula;

import com.example.bean.AlumnoBean;
import com.example.dao.AlumnoDAO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class Secundario extends Activity {

	EditText txtmensaje;
	TextView lblnombre;
	AlumnoBean objalAlumnoBean;
	AlumnoDAO obAlumnoDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secundario);
		txtmensaje=(EditText)findViewById(R.id.txtmensaje);
		lblnombre=(TextView)findViewById(R.id.lblnombre);
		Bundle ioextra=getIntent().getExtras();
		String facultad =ioextra.getString("facultad"); 
		String nombre =ioextra.getString("nombre"); 
		objalAlumnoBean=new AlumnoBean();
		objalAlumnoBean.setFacultad(Integer.parseInt(facultad));
		obAlumnoDAO = new AlumnoDAO();
		String msj=obAlumnoDAO.CalcularOperacion(objalAlumnoBean);
		lblnombre.setText(nombre);
		txtmensaje.setText(msj);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secundario, menu);
		return true;
	}

}
