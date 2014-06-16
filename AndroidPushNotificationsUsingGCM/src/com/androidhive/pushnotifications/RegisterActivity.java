package com.androidhive.pushnotifications;

import static com.androidhive.pushnotifications.CommonUtilities.SENDER_ID;
//import static com.androidhive.pushnotifications.CommonUtilities.SERVER_URL;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Internet detector
	ConnectionDetector cd;
	
	// UI elements
	EditText txtName;
	EditText txtEmail;
	EditText txtDireccionIP;
	
	// Register button
	Button btnRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		txtDireccionIP=(EditText)findViewById(R.id.txtDir);
		
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(RegisterActivity.this,
					"Error Coneccion Internet",
					"Por favor conecta tu conexion a internet", false);
			// stop executing code by return
			return;
		}

		txtName = (EditText) findViewById(R.id.txtName);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		/*
		 * Click event on Register button
		 * */
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Read EditText dat
				String name = txtName.getText().toString();
				String email = txtEmail.getText().toString();
				String dire = txtDireccionIP.getText().toString();
				
				// Check if GCM configuration is set
				
				//SERVER_URL="http://"+txtDireccionIP.getText()+"/gcm_server_php/register.php";
				
//				if (txtDireccionIP.getText() == null || SENDER_ID == null || txtDireccionIP.getText().length() == 0
//						|| SENDER_ID.length() == 0) {
//					// GCM sernder id / server url is missing
//					alert.showAlertDialog(RegisterActivity.this, "Error de Configuracion!",
//							"Por favor ingrese su direccion IP de su maquina en la cual se encuentra el localhost", false);
//					// stop executing code by return
//					 return;
//				}
				
				// Check if user filled the form
				if(dire.trim().length() > 0 && name.trim().length() > 0 && email.trim().length() > 0){
					// Launch Main Activity
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					
					// Registering user on our server					
					// Sending registraiton details to MainActivity
					i.putExtra("name", name);
					i.putExtra("email", email);
					i.putExtra("dire", dire);
					startActivity(i);
					finish();
				}else{
					// user doen't filled that data
					// ask him to fill the form
					alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "Please enter your details", false);
				}
			}
		});
	}

}
