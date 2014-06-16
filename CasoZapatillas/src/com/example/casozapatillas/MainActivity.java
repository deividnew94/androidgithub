package com.example.casozapatillas;


import com.example.bean.ProductoBean;
import com.example.dao.ProductoDAO;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener {

	Spinner cbomarca;
	Spinner cbotalla;
	EditText txtnumpares;
	EditText txtzapatillas;
	Button btncalcular;
	ProductoDAO pdao;
	ProductoBean pbean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cbomarca=(Spinner)findViewById(R.id.cbomarca);
		cbotalla=(Spinner)findViewById(R.id.cbotalla);
		txtnumpares=(EditText)findViewById(R.id.txtnumpares);
		txtzapatillas=(EditText)findViewById(R.id.txtzapatillas);
		btncalcular=(Button)findViewById(R.id.btncalcular);
		
		btncalcular.setOnClickListener(this);

	}
	

	public void Calcular(){
		pdao = new ProductoDAO();
		pbean= new ProductoBean();
		pbean.setMarca(cbomarca.getSelectedItemPosition());
		pbean.setTalla(cbotalla.getSelectedItemPosition());
		pbean.setNumpares(Integer.parseInt(txtnumpares.getText().toString()));
		txtzapatillas.setText("\n"+pdao.CalcularOperacion(pbean));
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
		switch (v.getId()) {
		case R.id.btncalcular:

			Calcular();
			break;

		}
	}

}
