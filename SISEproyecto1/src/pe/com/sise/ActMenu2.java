package pe.com.sise;

import pe.com.sise.bean.BeanMapper;
import pe.com.sise.bean.BeanUsuario;
import greendroid.app.GDActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ActMenu2 extends GDActivity implements OnItemClickListener {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	
	private ListView loLista;
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private String arrMenu[]={"inicio 2","sincroniza 2r","envio pendientes","consulta de stock", "reportes","consolidados","enpadronamiento","Encuestas"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.menu2);
		setActionBarContentView(R.layout.menu2);
		loLista=(ListView)findViewById(R.id.lista);
		ArrayAdapter<String> adaMenu=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrMenu);
		loLista.setAdapter(adaMenu);
		loLista.setOnItemClickListener(this);
		// TODO Put your code here
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "0=" + arg0.getId() + " 1="+ arg1.getId() + "  2=" + arg2 + "  3=" + arg3, Toast.LENGTH_LONG).show();
		if (arg2==2)
		{			
			BeanUsuario usu=new BeanUsuario();
			//usu.setCodigo("001");
			usu.setClave("1");
			String lscadena=BeanMapper.toJson(usu, false);
			Intent in = new Intent(this, ActEnvios.class);
			in.putExtra("usu", lscadena);
			startActivity(in);	
			Log.v("json", lscadena);
		}
	}
}
