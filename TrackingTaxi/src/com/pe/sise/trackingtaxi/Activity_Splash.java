package com.pe.sise.trackingtaxi;

import com.pe.sise.trackingtaxi.dal.DalConfiguracion;

import android.location.Location;
import android.location.LocationListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.WindowManager;

public class Activity_Splash extends Activity implements LocationListener {
	protected boolean ibActivo = true;
	protected int itiempo = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		MediaPlayer loMediaPlayer = MediaPlayer.create(this, R.raw.claxon_taxi);
		loMediaPlayer.start();
		
		DalConfiguracion loDalConfiguracion=new DalConfiguracion(this);//llmar a la clase para poder conectarse a la BD
		loDalConfiguracion.subDataFramework(getPackageName());//
		
		Thread loTreadSplash = new Thread(){

			@Override
			public void run() {
				try {
				    
				    
					int waited = 0;
					while(ibActivo && (waited < itiempo)){
						sleep(100);
						if(ibActivo){
							waited +=100;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					startActivity(new Intent(Activity_Splash.this, Activity_Login.class));
					interrupt();
				}
			}
		};
		loTreadSplash.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.layout_splash, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			ibActivo = false;
		}
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	

}
