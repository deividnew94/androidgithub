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
import com.pe.sise.trackingtaxi.dal.DalCliente;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Registrate extends GDActivity {
	

    /**
     *  JSON Response node names.
     **/


    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_FIRSTNAME = "fname";
    private static String KEY_LASTNAME = "lname";
    private static String KEY_USERNAME = "uname";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    private static String KEY_ERROR = "error";

    /**
     * Defining layout items.
     **/

    EditText inputFirstName;
    EditText inputLastName;
    Spinner inputUsername;
    EditText inputEmail;
    EditText inputPassword;
    Button btnRegister;
    TextView registerErrorMsg;


    /**
     * Called when the activity is first created.
     */
	
	DalCliente loDalCliente = new DalCliente(this);
	
	
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.activity_register);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		inputFirstName = (EditText) findViewById(R.id.fname);
        inputLastName = (EditText) findViewById(R.id.lname);
        inputUsername = (Spinner) findViewById(R.id.uname2);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.pword);
        btnRegister = (Button) findViewById(R.id.register);
        registerErrorMsg = (TextView) findViewById(R.id.register_error);
        

/**
 * Button which Switches back to the login screen on clicked
 **/

        Button login = (Button) findViewById(R.id.bktologin);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity_Login.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });

        /**
         * Register Button click event.
         * A Toast is set to alert when the fields are empty.
         * Another toast is set to alert Username must be 5 characters.
         **/

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (  ( !inputUsername.getSelectedItem().toString().equals("")) && ( !inputPassword.getText().toString().equals("")) && ( !inputFirstName.getText().toString().equals("")) && ( !inputLastName.getText().toString().equals("")) && ( !inputEmail.getText().toString().equals("")) )
                {
                    if ( inputUsername.getSelectedItem().toString().length() > 4 ){
                    NetAsync(view);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),
                                "Username should be minimum 5 characters", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
	}

	/**
     * Async Task to check whether internet connection is working
     **/

    private class NetCheck extends AsyncTask<String,String,Boolean>
    {
        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            nDialog = new ProgressDialog(Activity_Registrate.this);
            nDialog.setMessage("Loading..");
            nDialog.setTitle("Checking Network");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args){


/**
 * Gets current device state and checks for working internet connection by trying Google.
 **/
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
                new ProcessRegister().execute();
            }
            else{
                nDialog.dismiss();
                registerErrorMsg.setText("Error in Network Connection");
            }
        }
    }
    


    private class ProcessRegister extends AsyncTask<String, String, JSONObject> {

/**
 * Defining Process dialog
 **/
        private ProgressDialog pDialog;

        String email,password,fname,lname,uname;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            inputUsername = (Spinner) findViewById(R.id.uname2);
            inputPassword = (EditText) findViewById(R.id.pword);
               fname = inputFirstName.getText().toString();
               lname = inputLastName.getText().toString();
                email = inputEmail.getText().toString();
                uname= inputUsername.getSelectedItem().toString();
                password = inputPassword.getText().toString();
            pDialog = new ProgressDialog(Activity_Registrate.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Registering ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {


        UserFunctions userFunction = new UserFunctions();
        JSONObject json = userFunction.registerUser(fname, lname, email, uname, password);

            return json;


        }
       @Override
        protected void onPostExecute(JSONObject json) {
       /**
        * Checks for success message.
        **/
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        registerErrorMsg.setText("");
                        String res = json.getString(KEY_SUCCESS);

                        String red = json.getString(KEY_ERROR);

                        if(Integer.parseInt(res) == 1){
                            pDialog.setTitle("Getting Data");
                            pDialog.setMessage("Loading Info");

                            registerErrorMsg.setText("Successfully Registered");


                            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");

                            /**
                             * Removes all the previous data in the SQlite database
                             **/

                            UserFunctions logout = new UserFunctions();
                            logout.logoutUser(getApplicationContext());
                            db.addUser(json_user.getString(KEY_FIRSTNAME),json_user.getString(KEY_LASTNAME),json_user.getString(KEY_EMAIL),json_user.getString(KEY_USERNAME),json_user.getString(KEY_UID),json_user.getString(KEY_CREATED_AT));
                            /**
                             * Stores registered data in SQlite Database
                             * Launch Registered screen
                             **/

                            Intent registered = new Intent(getApplicationContext(), Registered.class);

                            /**
                             * Close all views before launching Registered screen
                            **/
                            registered.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            pDialog.dismiss();
                            startActivity(registered);


                              finish();
                        }

                        else if (Integer.parseInt(red) ==2){
                            pDialog.dismiss();
                            registerErrorMsg.setText("User already exists");
                        }
                        else if (Integer.parseInt(red) ==3){
                            pDialog.dismiss();
                            registerErrorMsg.setText("Invalid Email id");
                        }

                    }


                        else{
                        pDialog.dismiss();

                            registerErrorMsg.setText("Error occured in registration");
                        }

                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }}
        public void NetAsync(View view){
            new NetCheck().execute();
        }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loyout_registrate, menu);
		return true;
	}

	/*@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegistrar:
			if (edtNombresApellidos.getText().toString().equals("") && edtTelefono.getText().toString().equals("") && edtEmail.getText().toString().equals("") && edtPassword.getText().toString().equals("")) 
			{
				Toast.makeText(this, "Ingresar todos los Registros", Toast.LENGTH_LONG).show();
			}
			else
			{
				
				//METODO REGISTRAR CLIENTE
			BeanCliente loBeanCliente = new BeanCliente();			
			loBeanCliente.setNombres(edtNombresApellidos.getText().toString());
			loBeanCliente.setTelefono(edtTelefono.getText().toString());
			loBeanCliente.setEmail(edtEmail.getText().toString());
			loBeanCliente.setContrasena(edtPassword.getText().toString());
			
			Boolean rpta = loDalCliente.subInsert(loBeanCliente);
			if (rpta) {
				Toast loToast = Toast.makeText(this, "CLIENTE REGISTRADO CORRECTAMENTE", Toast.LENGTH_LONG);
				loToast.setGravity(Gravity.CENTER, 0, 0);
				loToast.show();
				Intent loIntent=new Intent(this, Activity_Login.class);				
		    	startActivity(loIntent);
			}
			else { Toast.makeText(this, "ERROR EN EL REGISTRO", Toast.LENGTH_LONG).show();}
			
			break;			
			}
		}
	}
	*/
	public void subMenu()
	{
		Toast.makeText(this, getHttpResultado(), Toast.LENGTH_LONG).show();
		if(getIdHttpResultado()==1)
		{
			Intent loIntent = new Intent(this, Activity_Login.class);
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
