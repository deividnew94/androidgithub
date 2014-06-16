package EMPRESA.ANDROID.PRINCIPAL;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MovilListView extends Activity implements OnItemClickListener{

	ListView listado=null;
	String depa[]={"Lima","Cuzco","Loreto"};
	
	ArrayList<Persona> listainicial=null;
	ArrayList<String> listafinal=null;
	String almacen[];
	
	EditText txtnombre,txtapellido;
	Button btnregresar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ventanainicial();
	}
	
	public ArrayList<Persona> datosiniciales(){
		try {
			listainicial=new ArrayList<Persona>();
			Persona obj1=new Persona();
			obj1.setNombre("david");
			obj1.setApellido("hernandez");
			listainicial.add(obj1);
			Persona obj2=new Persona();
			obj2.setNombre("safsad");
			obj2.setApellido("safsadf");
			listainicial.add(obj2);
			Persona obj3=new Persona();
			obj3.setNombre("qqqqqqqqqqq");
			obj3.setApellido("wwwwwwwwww");
			listainicial.add(obj3);
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return listainicial;
	}
	
	public ArrayList<String> datosfinales(){
		listainicial=null;
		String acum="";
		try {
			listafinal= new ArrayList<String>();
			listainicial=datosiniciales();
			for(Persona obj:listainicial){
				acum=obj.getNombre()+"-"+obj.getApellido();
				listafinal.add(acum);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listafinal;
	}
	
	public String[] cargadatos(){
		listafinal=null;
		String x[]=null;
		int i=0;
		try {
			listafinal=datosfinales();
			x=new String[listafinal.size()];
			for (String obj : listafinal) {
				x[i]=obj;
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return x;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movil_list_view, menu);
		return true;
	}

	public void seleccionar(View arg1){
		String cadena=((TextView)arg1).getText().toString();
		String x[]=cadena.split("-");
		venatanareporte(x[0], x[1]);
		Toast.makeText(getApplicationContext(),x[0]+"	"+x[1]+"	"+x[2],2000).show();
	}
	
	public void regresar(View v){
		ventanainicial();
	}
	public void ventanainicial(){

		setContentView(R.layout.main);
		almacen=cargadatos();
		listado=(ListView)findViewById(R.id.lstopciones);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.b,depa);
		//listado.setAdapter(adapter);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.b,almacen);
		listado.setAdapter(adapter);
		listado.setOnItemClickListener(this);
	}
	
	public void venatanareporte(String nombre, String apellido){
		setContentView(R.layout.reporte);
		txtnombre=(EditText)findViewById(R.id.txtnombre);
		txtapellido=(EditText)findViewById(R.id.txtapellido);
		txtnombre.setText(nombre);
		txtapellido.setText(apellido);
		btnregresar=(Button)findViewById(R.id.btnregresar);
		btnregresar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				regresar(v);
			}
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		seleccionar(arg1);
	}

}
