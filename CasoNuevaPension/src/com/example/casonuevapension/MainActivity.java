package com.example.casonuevapension;

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

public class MainActivity extends Activity implements OnCheckedChangeListener,OnClickListener{

	RadioGroup rbtcategoria;
	RadioGroup rbttipoalumno;
	EditText txtpromedio;
	Button btncalcular;
	EditText txtmensaje;
	RadioButton rba,rbb,rbc,rbd,rbinterno,rbexterno;
	String categoria="A",tipo="INTERNO";
	AlumnoBean objAlumnoBean;
	AlumnoDAO objAlumnoDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rbtcategoria=(RadioGroup)findViewById(R.id.rgbcategoria);
		rbttipoalumno=(RadioGroup)findViewById(R.id.rgbtipoalumno);
		txtpromedio=(EditText)findViewById(R.id.txtpromedio);
		btncalcular=(Button)findViewById(R.id.btncalcular);
		txtmensaje=(EditText)findViewById(R.id.txtmensaje);
		rba=(RadioButton)findViewById(R.id.rba);
		rbb=(RadioButton)findViewById(R.id.rbb);
		rbc=(RadioButton)findViewById(R.id.rbc);
		rbd=(RadioButton)findViewById(R.id.rbd);
		rbexterno=(RadioButton)findViewById(R.id.rbe);
		rbinterno=(RadioButton)findViewById(R.id.rbi);
		rbtcategoria.setOnCheckedChangeListener(this);
		rbttipoalumno.setOnCheckedChangeListener(this);
		btncalcular.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
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
		objAlumnoBean.setTipo(tipo);
		objAlumnoBean.setPromedio(promedio);
		objAlumnoDAO=new AlumnoDAO();
		String msj=objAlumnoDAO.CalcularOperacion(objAlumnoBean);
		txtmensaje.setText(msj);
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		//int pos = group.indexOfChild (findViewById (checkedId));
		//	Toast.makeText(this, ""+ (group.getChildAt(pos).getId()), Toast.LENGTH_LONG).show();
		if(group==rbtcategoria)
		{
			if(rba.isChecked()){
				categoria=rba.getText().toString();
			}
			if(rbb.isChecked()){
				categoria=rbb.getText().toString();
				
			}
			if(rbc.isChecked()){

				categoria=rbc.getText().toString();
			}
			if(rbd.isChecked()){

				categoria=rbd.getText().toString();
			}
		}
		if(group==rbttipoalumno)
		{
			if(rbinterno.isChecked()){

				tipo=rbinterno.getText().toString();
			}
			if(rbexterno.isChecked()){

				tipo=rbexterno.getText().toString();
			}
		}
	}

}
