package pe.com.sise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActMenu extends Activity implements OnItemClickListener {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	private TextView lblMensaje; 
	private String arrMenu[]={"inicio","sincronizar","envio pendientes","consulta de stock", "reportes","consolidados","enpadronamiento","Encuestas"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mwnu);
		Bundle loExtras = getIntent().getExtras();
		String lsCodigo=loExtras.getString("codUsuario");
		ListView loLista=(ListView) findViewById(R.id.ActMenuLista);
		ArrayAdapter<String> adaMenu=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrMenu);
		loLista.setAdapter(adaMenu);
		loLista.setOnItemClickListener(this);
		
		String lsCodUsuario =getSharedPreferences("Param", MODE_PRIVATE).getString("codusuario", "sin valor");
		Log.v("sharedpreferences", lsCodUsuario);
	}
	
	//arg2=posicion, arg3=id, 
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "0=" + arg0.getId() + " 1=" + arg1.getId() + " 2=" + arg2 +" 3="+arg3, Toast.LENGTH_LONG).show();
		if (arg2==2)
		{
			Intent lo = new Intent(this, ActSplash.class);
			startActivity(lo);
		}
	}
}
