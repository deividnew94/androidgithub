package com.example.utppension;

import com.example.bean.AlumnoBean;
import com.example.dao.AlumnoDAO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Principal extends Activity implements OnCheckedChangeListener,OnClickListener{

	RadioGroup rbtcategoria;
	EditText txtpromedio;
	Button btncalcular;
	EditText txtmensaje;
	RadioButton rba,rbb,rbc,rbd;
	String categoria="A";
	AlumnoBean objAlumnoBean;
	AlumnoDAO objAlumnoDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		rbtcategoria=(RadioGroup)findViewById(R.id.rgbcategoria);
		txtpromedio=(EditText)findViewById(R.id.txtpromedio);
		btncalcular=(Button)findViewById(R.id.btncalcular);
		txtmensaje=(EditText)findViewById(R.id.txtmensaje);
		rba=(RadioButton)findViewById(R.id.rba);
		rbb=(RadioButton)findViewById(R.id.rbb);
		rbc=(RadioButton)findViewById(R.id.rbc);
		rbd=(RadioButton)findViewById(R.id.rbd);
		rbtcategoria.setOnCheckedChangeListener(this);
		btncalcular.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Calcular();
	}


	public void Calcular(){
		int promedio=0;
		if(txtpromedio.getText().length()>0){
			promedio=Integer.parseInt(txtpromedio.getText().toString());
		}
		objAlumnoBean=new AlumnoBean();
		objAlumnoBean.setCategorias(categoria);
		objAlumnoBean.setPromedio(promedio);
		objAlumnoDAO=new AlumnoDAO();
		String msj=objAlumnoDAO.CalcularOperacion(objAlumnoBean);
		txtmensaje.setText(msj);
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		//int pos = group.indexOfChild(findViewById(checkedId));
		if(group==rbtcategoria)
		{
/*			for ( int i = 0 ; i < group.getChildCount(); i ++)  { 
	            RadioButton btn =  (RadioButton)group.getChildAt(i); 
	            if ( btn.getId() == checkedId )  { 
	                 String text = btn.getText().toString(); 
	                 // hacer algo con el texto 
	                 categoria=text;
	             	//Toast.makeText(this, ""+ categoria, Toast.LENGTH_LONG).show();
	            } 
	       }*/
			int radioBtnChecked = group . getCheckedRadioButtonId ();       
			RadioButton rbtn =  (RadioButton)findViewById(radioBtnChecked); 
			categoria = rbtn.getText().toString();
	   } 
		//Toast.makeText(this, ""+ (group.getChildAt(pos)), Toast.LENGTH_LONG).show();
	}

}
