package SISE.ANDROID.PRINCIPAL;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import SISE.ANDROID.BEAN.Persona;
import SISE.ANDROID.DAO.PersonaDAO;

public class Principal extends Activity implements OnClickListener
{
	EditText TXTNOMBRE, TXTAPELLIDO, TXTDNI, TXTRESULTADO;
	Button   BTNAGREGAR, BTNLISTAR;
	
	ArrayList<Persona> lista;
	private PersonaDAO objPersonaDAO;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        TXTNOMBRE=(EditText)findViewById(R.id.TXTNOMBRE);
        TXTAPELLIDO=(EditText)findViewById(R.id.TXTAPELLIDO);
        TXTDNI=(EditText)findViewById(R.id.TXTDNI);
        TXTRESULTADO=(EditText)findViewById(R.id.TXTRESULTADO);
        BTNAGREGAR=(Button)findViewById(R.id.BTNAGREGAR);
        BTNAGREGAR.setOnClickListener(this);
        BTNLISTAR=(Button)findViewById(R.id.BTNLISTAR);
        BTNLISTAR.setOnClickListener(this);
        
        
        objPersonaDAO=new PersonaDAO(this);
        objPersonaDAO.open(); //abrir la conexion
    }
	@Override
	public void onClick(View v) 
	{
		if(v==BTNAGREGAR)
		{
			Grabar();
		}
		if(v==BTNLISTAR)
		{
			cargarTabla();
		}
	}
	
	public void Grabar()
	{
		String nombre,apellido,dni;
		nombre=TXTNOMBRE.getText().toString();
		apellido=TXTAPELLIDO.getText().toString();
		dni=TXTDNI.getText().toString();
		Persona objPersona=new Persona();
				objPersona.setNombre(nombre);
				objPersona.setApellido(apellido);
				objPersona.setDni(dni);
		long estado=objPersonaDAO.Insertar(objPersona);
		if(estado==0)
		{
			Toast.makeText(getApplicationContext(), "Registro No Insertado", 5000).show();
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Registro Insertado", 5000).show();
		}		
		TXTNOMBRE.setText("");
		TXTAPELLIDO.setText("");
		TXTDNI.setText("");
		TXTNOMBRE.requestFocus();
	}
	
	public void cargarTabla()
	{
		String acum="";
		lista = objPersonaDAO.ListadoGeneral();
		for(Persona obj:lista)
		{
			acum = acum+obj.getCodigo()+"  "+
					    obj.getNombre()+"  "+
					    obj.getApellido()+"  "+
					    obj.getDni()+"\n";
		}	
		TXTRESULTADO.setText(acum);
	}
}
