package com.pe.sise.trackingtaxi;

import static com.pe.sise.trackingtaxi.push.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.pe.sise.trackingtaxi.push.CommonUtilities.EXTRA_MESSAGE;
import static com.pe.sise.trackingtaxi.push.CommonUtilities.SENDER_ID;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.pe.sise.trackingtaxi.library.DatabaseHandler;
import com.pe.sise.trackingtaxi.push.ServerUtilities;

import greendroid.app.GDActivity;

import java.util.HashMap;

public class Registered extends GDActivity {

	AsyncTask<Void, Void, Void> mRegisterTask;

    /**
     * Called when the activity is first created.
     */
	
	DatabaseHandler db;
	HashMap<String,String> user;
	
	public static String name;
	public static String correo;
	public static String ip;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.registered);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        
        try {
	    	   db = new DatabaseHandler(getApplicationContext());
	    	   user = new HashMap<String, String>();
		       user = db.getUserDetails();
	       } catch (Exception e) {
	    	   // TODO: handle exception
	    	   e.printStackTrace();
	       }

        /**
         * Displays the registration details in Text view
         **/

        final TextView fname = (TextView)findViewById(R.id.fname);
        final TextView lname = (TextView)findViewById(R.id.lname);
        final TextView uname = (TextView)findViewById(R.id.uname);
        final TextView email = (TextView)findViewById(R.id.email);
        final TextView created_at = (TextView)findViewById(R.id.regat);
        fname.setText(user.get("fname"));
        lname.setText(user.get("lname"));
        uname.setText(user.get("uname"));
        email.setText(user.get("email"));
        created_at.setText(user.get("created_at"));

        
        name = user.get("fname")+" "+user.get("lname");
		correo = user.get("email");	
		ip = "misitiodemostracion.site90.net";	
        
        
        	// Make sure the device has the proper dependencies.
     		GCMRegistrar.checkDevice(this);

     		// Make sure the manifest was properly set - comment out this line
     		// while developing the app, then uncomment it when it's ready.
     		GCMRegistrar.checkManifest(this);

    
     		
     	// Get GCM registration id
    		final String regId = GCMRegistrar.getRegistrationId(this);

    		// Check if regid already presents
    		if (regId.equals("")) {
    			// Registration is not present, register now with GCM			
    			GCMRegistrar.register(this, SENDER_ID);
    		} else {
    			// Device is already registered on GCM
    			if (GCMRegistrar.isRegisteredOnServer(this)) {
    				// Skips registration.				
    				Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
    			} else {
    				// Try to register again, but not in the UI thread.
    				// It's also necessary to cancel the thread onDestroy(),
    				// hence the use of AsyncTask instead of a raw thread.
    				final Context context = this;
    				mRegisterTask = new AsyncTask<Void, Void, Void>() {

    					@Override
    					protected Void doInBackground(Void... params) {
    						// Register on our server
    						// On server creates a new user
    						ServerUtilities.register(context, name, correo,ip, regId);
    						return null;
    					}

    					@Override
    					protected void onPostExecute(Void result) {
    						mRegisterTask = null;
    					}

    				};
    				mRegisterTask.execute(null, null, null);
    			}
    		}

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity_Login.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });


    }
    
    
}
