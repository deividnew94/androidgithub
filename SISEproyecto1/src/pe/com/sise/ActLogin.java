package pe.com.sise;

import pe.com.sise.bean.BeanUsuario;
import pe.com.sise.dat.DalCFCorrelativo;
import pe.com.sise.http.HttpLogin;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActLogin extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private EditText txtUsuario;
	private EditText txtPassword;
	private Button btnAceptar;
	private final int CONSVALIDAR=1;
	private final int CONSDIABIENVENDA=2;
	
	//---------------------------------------
		//propiedades para la conexion Http
		//---------------------------------------
		private String httpResultado;
		private int idHttpResultado;
		public String getHttpResultado() {
			return httpResultado;
			}
		public void setHttpResultado(String psHttpResultado) {
			this.httpResultado = psHttpResultado;
			}
		public void setIdHttpResultado(int idHttpResultado) {
			this.idHttpResultado = idHttpResultado;
		}
		public int getIdHttpResultado() {
			return idHttpResultado;
		}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("evento", "onCreate()");
        setContentView(R.layout.actlogin);
        this.setTitle("login");
        txtUsuario=(EditText)findViewById(R.id.actloginTxtUsuario);
        txtPassword=(EditText)findViewById(R.id.actLoginTxtPassword);
        btnAceptar=(Button)findViewById(R.id.actLoginBtnAceptar);
        btnAceptar.setOnClickListener(this);
        DalCFCorrelativo a = new DalCFCorrelativo(this);
        a.subLeer("5");
    }

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) 
		{
			case R.id.actLoginBtnAceptar:
			if (txtPassword.getText().toString().equals("") || txtUsuario.getText().toString().equals(""))
			{
				//showDialog(CONSVALIDAR);
				Toast.makeText(this, "ingrese un usuario y o password", Toast.LENGTH_LONG).show();
			}
			else
			{
				BeanUsuario loBeanUsuario=new BeanUsuario();
				loBeanUsuario.setClave("415d2e4353963c539961c46ad289844f4093ba96");
				loBeanUsuario.setLogin(txtUsuario.getText().toString());
				HttpLogin loHttpLogin=new HttpLogin(ActLogin.this, "Enviando al servidor",loBeanUsuario);
            	loHttpLogin.execute();
			}
			break;
		}
	}

	/*
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog loAlertDialog=null;
		
		switch (id) 
    	{
    	case CONSVALIDAR:
    		loAlertDialog= new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_info)
            .setTitle("llene los valores")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	
                	if(txtUsuario.getText().toString().equals(""))
                	{
                		txtUsuario.requestFocus();
                	}
                	else if(txtPassword.getText().toString().equals(""))
                	{
                		txtPassword.requestFocus();
                	}
                }
            })
            .create();
           break;
    	case CONSDIABIENVENDA:
    		loAlertDialog= new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_info)
            .setTitle( "bienvenido :" + txtUsuario.getText().toString())
            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	SharedPreferences loSharedPreferences=getSharedPreferences("Param", MODE_PRIVATE);
                	Editor loEditor=loSharedPreferences.edit();
                	loEditor.putString("codusuario", "122");
                	loEditor.commit();
                	//
                	//subMenu();
                	
                	HttpLogin loHttpLogin=new HttpLogin(ActLogin.this, "mensaje");
                	loHttpLogin.execute();
                	//Intent loIntent=new Intent(ActLogin.this, ActMenu2.class);
                	//Intent loIntent=new Intent(ActLogin.this, ActNormal.class);
                	//loIntent.putExtra("codUsuario", "12");
                	//startActivity(loIntent);
                	
                }
            })
            .create();
           break;
        default:
        	
            break;
    	}
		return loAlertDialog;
	}
	
	*/
	public void subMenu()
	{
		Toast.makeText(this, getHttpResultado(), Toast.LENGTH_LONG).show();
		if(getIdHttpResultado()==1)
		{
			Intent loIntent = new Intent(this, ActMenu2.class);
			loIntent.putExtra("NomVariable", "Valor");
			startActivity(loIntent);
		}
		
		
	}
	public void suberror()
	{
		Toast loToast = Toast.makeText(this, "error de conexion", Toast.LENGTH_LONG);
		loToast.setGravity(Gravity.CENTER, 0, 0);
		loToast.show();
	}
}