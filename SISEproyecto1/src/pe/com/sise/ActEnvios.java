package pe.com.sise;

import pe.com.sise.bean.BeanMapper;
import pe.com.sise.bean.BeanUsuario;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class ActEnvios extends Activity {

	String codigo,clave,apellido,nombre;
	
	private TextView txtcodigo;
	private TextView txtclave;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.envios);
		Bundle ioextra=getIntent().getExtras();
		String z =ioextra.getString("usu");
		BeanUsuario a=(BeanUsuario)BeanMapper.fromJson(z, BeanUsuario.class);
		txtcodigo=(TextView)findViewById(R.id.txtcodigo);
		//txtcodigo.setText(a.getCodigo());
		txtclave=(TextView)findViewById(R.id.txtclave);
		txtclave.setText(a.getClave());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.envios, menu);
		return true;
	}

}
