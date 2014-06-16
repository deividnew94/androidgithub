package com.pe.sise.trackingtaxi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import greendroid.app.GDActivity;

import com.pe.sise.trackingtaxi.library.DatabaseHandler;
import com.pe.sise.trackingtaxi.library.UserFunctions;
import com.pe.sise.trackingtaxi.util.Gps;

import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pe.sise.trackingtaxi.push.ConnectionDetector;;

public class Activity_Login extends GDActivity {
	Button btnLogin;
    Button Btnregister;
    Button passreset;
    EditText inputEmail;
    EditText inputPassword;
    private TextView loginErrorMsg;
    
    ConnectionDetector cd;
    
    
    /**
     * Called when the activity is first created.
     */
    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_USERNAME = "uname";
    private static String KEY_FIRSTNAME = "fname";
    private static String KEY_LASTNAME = "lname";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    
    
    //ESTO SIEMPRE VA SON PARA IMPRIMIR LOS RESULTADOS DE LA TRANSFERENCIA HTTP
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
	/////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.layout_login);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setTitle("Login Cliente");
		inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.pword);
        Btnregister = (Button) findViewById(R.id.registerbtn);
        btnLogin = (Button) findViewById(R.id.login);
        passreset = (Button)findViewById(R.id.passres);
        loginErrorMsg = (TextView) findViewById(R.id.loginErrorMsg);
        
        inputEmail.setText("deividnew94@gmail.com");
        inputPassword.setText("123");
        
        
		
		passreset.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View view) {
	        Intent myIntent = new Intent(view.getContext(), Activity_OlvideMiPassword.class);
	        startActivityForResult(myIntent, 0);
	        finish();
	        }});
		
		Btnregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity_Registrate.class);
                startActivityForResult(myIntent, 0);
                finish();
             }});
		
		btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	startService(new Intent(Activity_Login.this,TestServicio.class));
				
                if (  ( !inputEmail.getText().toString().equals("")) && ( !inputPassword.getText().toString().equals("")) )
                {
                    NetAsync(view);
                }
                else if ( ( !inputEmail.getText().toString().equals("")) )
                {
                    Toast.makeText(getApplicationContext(),
                            "Password field empty", Toast.LENGTH_SHORT).show();
                }
                else if ( ( !inputPassword.getText().toString().equals("")) )
                {
                    Toast.makeText(getApplicationContext(),
                            "Email field empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Email and Password field are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
		
//		if (!cd.isConnectingToInternet()) {
//			// Internet Connection is not present
//			alert.showAlertDialog(Activity_Login.this,
//					"Error Coneccion Internet",
//					"Por favor conecta tu conexion a internet", false);
//			// stop executing code by return
//			//System.exit(0);
//			return;
//		}
		
		
//		if(!Gps.startListening(this)){
//			Gps.showSettingsAlert(getApplicationContext());
//		}
		
//		if (Gps.startListening(this)== false) {
//			// Internet Connection is not present
//			alert.showAlertDialog(Activity_Login.this,
//					"Error Coneccion GPS",
//					"Por favor active su GPS", false);
//			// stop executing code by return
//			//System.exit(0);
//			return;
//		}
		

		
	}


/**
 * Async Task to check whether internet connection is working.
 **/

    private class NetCheck extends AsyncTask<String,String,Boolean>
    {
        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            nDialog = new ProgressDialog(Activity_Login.this);
            nDialog.setTitle("Checking Network");
            nDialog.setMessage("Loading..");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }
        /**
         * Gets current device state and checks for working internet connection by trying Google.
        **/
        @Override
        protected Boolean doInBackground(String... args){



            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return false;

        }
        @Override
        protected void onPostExecute(Boolean th){

            if(th == true){
                nDialog.dismiss();
                new ProcessLogin().execute();
            }
            else{
                nDialog.dismiss();
                loginErrorMsg.setText("Error in Network Connection");
            }
        }
    }

    /**
     * Async Task to get and send data to My Sql database through JSON respone.
     **/
    private class ProcessLogin extends AsyncTask<String, String, JSONObject> {


        private ProgressDialog pDialog;

        String email,password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            inputEmail = (EditText) findViewById(R.id.email);
            inputPassword = (EditText) findViewById(R.id.pword);
            email = inputEmail.getText().toString();
            password = inputPassword.getText().toString();
            pDialog = new ProgressDialog(Activity_Login.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Logging in ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();
            JSONObject json = userFunction.loginUser(email, password);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
               if (json.getString(KEY_SUCCESS) != null) {

                    String res = json.getString(KEY_SUCCESS);

                    if(Integer.parseInt(res) == 1){
                        pDialog.setMessage("Loading User Space");
                        pDialog.setTitle("Getting Data");
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_user = json.getJSONObject("user");
                        /**
                         * Clear all previous data in SQlite database.
                         **/
                        UserFunctions logout = new UserFunctions();
                        logout.logoutUser(getApplicationContext());
                        db.addUser(json_user.getString(KEY_FIRSTNAME),json_user.getString(KEY_LASTNAME),json_user.getString(KEY_EMAIL),json_user.getString(KEY_USERNAME),json_user.getString(KEY_UID),json_user.getString(KEY_CREATED_AT));
                       /**
                        *If JSON array details are stored in SQlite it launches the User Panel.
                        **/
                        Intent upanel = new Intent(getApplicationContext(), Activity_Ubicacion.class);
                        upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        pDialog.dismiss();
                        startActivity(upanel);
                        /**
                         * Close Login Screen
                         **/
                        finish();
                    }else{

                        pDialog.dismiss();
                        loginErrorMsg.setText("Incorrect username/password");
                        LayoutInflater inflater = getLayoutInflater();
    			        View layout = inflater.inflate(R.layout.toast1,null);
    			        Toast toast = new Toast(getApplicationContext());
    			        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    			        toast.setDuration(Toast.LENGTH_LONG);
    			        toast.setView(layout);
    			        toast.show(); 
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
       }
    }
    public void NetAsync(View view){
        new NetCheck().execute();
    }
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		cd = new ConnectionDetector(getApplicationContext());
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	    //ocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, LocationListener);
	    boolean isGPS = locationManager.isProviderEnabled (LocationManager.GPS_PROVIDER);

	    if(!isGPS || !cd.isConnectingToInternet()){
	    	
	    	if(!isGPS && !cd.isConnectingToInternet()){
	    		Gps.showSettingsAlert(this,1);
	    	}else if(!isGPS){
	    		Gps.showSettingsAlert(this,0);
	    	}else if(!cd.isConnectingToInternet()){
	    		Gps.showSettingsAlert(this,2);
	    	}
	    }else{
			Gps.startListening(this);
	    }
	    
	    
	}

	//ESTE ES EL CONDUCTOR AL SIGUIENTE ACTIVITY SI EL LOGUEO SALIO BIEN
	public void subMenu()
	{
		Toast.makeText(this, getHttpResultado(), Toast.LENGTH_LONG).show();
		if(getIdHttpResultado()==1)
		{
			Intent loIntent = new Intent(this, Activity_Ubicacion.class);
			loIntent.putExtra("NomVariable", "Valor");
			startActivity(loIntent);
		}		
	}
	//////////////////////////////////////////////////////////////////////
	//ESTE ES EL MENSAJE DE ERROR DE CONEXION
	public void suberror()
	{
		Toast loToast = Toast.makeText(this, "error de conexion", Toast.LENGTH_LONG);
		loToast.setGravity(Gravity.CENTER, 0, 0);
		loToast.show();
	}
	////////////////////////////////////////////////////////////////////
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.layout_login, menu);
		return true;
	}

	/*@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnIniciarSesion:
			if(edtPassword.getText().toString().equals("") || edtEmail.getText().toString().equals(""))
			{
				Toast.makeText(this, "Ingresar Email y Password", Toast.LENGTH_LONG).show();
				 
		        
			}else{
				Log.v("msg", "2");
				BeanCliente loBeanCliente = new BeanCliente();
				
				loBeanCliente.setEmail(edtEmail.getText().toString());
				loBeanCliente.setContrasena(edtPassword.getText().toString());
				
				DalCliente loDalCliente= new DalCliente(this);
				
				BeanCliente lobeanBeanclienteRes = loDalCliente.subReading(loBeanCliente);
				
				String email = BeanMapper.toJson(lobeanBeanclienteRes, false);
				
				
				
				if (lobeanBeanclienteRes!=null) 
				{
					Log.v("Ok", lobeanBeanclienteRes.getNombres());
					Intent loIntent = new Intent(this, Activity_Ubicacion.class);
					loIntent.putExtra("email", email);
					startActivity(loIntent);
				}
				else
				{
					LayoutInflater inflater = getLayoutInflater();
			        View layout = inflater.inflate(R.layout.toast1,null);
			        Toast toast = new Toast(getApplicationContext());
			        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			        toast.setDuration(Toast.LENGTH_LONG);
			        toast.setView(layout);
			        toast.show(); 
				}
				
				
				//METODO DONDE SE SETEA BEAN Y SE LLAMA AL HTTP DESPUES
				//TU CODIGO DE ARRIBA Q E COMENTADO DESPUES LO ACOMODAS	CON EL DE ABAJO PARA QUE LANZE UN MENSAJE SI ES USUARIO INCORRECTO		
				
					BeanTaxista loBeanTaxista=new BeanTaxista();
					//SI NO SABES DE DONDE SACAR EL PARAMETRO Q NECESITAS PARA ENVIAR X EJEMPLO POSICION, ETC LE ENVIAS EN DURO YO DESPUES PONGO EL VALOR CORRECTO CUANDO VENGA LE DICES A PAMPA
					loBeanTaxista.setContrasena(edtPassword.getText().toString());//LLENO DATOS
					loBeanTaxista.setEmail(edtEmail.getText().toString());//LLENO DATOS
					Http_ActivityLogin loHttpLogin=new Http_ActivityLogin(Activity_Login.this, "Enviando al servidor",loBeanTaxista);//envio a clase http
	            	loHttpLogin.execute();//ejecuto metodo
	            	
            	//////////////////////////////////////////////////////////////////////////////////////
			}
			break;
			
		case R.id.btnRegistrate:
			Intent loIntent=new Intent(Activity_Login.this, Activity_Registrate.class);
			loIntent.putExtra("menu", "menu2");
	    	startActivity(loIntent);	    	
	    	break;	
		case R.id.txtvOlvideMiPassword:
			Intent in = new Intent(this,Activity_OlvideMiPassword.class);
			startActivity(in);
			break;
		}
	
	}*/
		
	}

