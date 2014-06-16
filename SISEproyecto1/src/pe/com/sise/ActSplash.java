package pe.com.sise;


import pe.com.sise.dat.DalConfiguracion;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

public class ActSplash extends Activity {
	protected boolean ibActivo = true;
	protected int iiTiempo = 3000; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		//seteo de los valores por defecto de las preferencias
		//PreferenceManager.setDefaultValues(this, R.xml.preferencias, false);
		
		
	    // thread for displaying the SplashScreen
	    Thread loTreadSplash = new Thread() {
	        @Override
	        public void run() {
	            try {
	                int waited = 0;
	                while(ibActivo && (waited < iiTiempo)) {
	                    sleep(100);
	                    if(ibActivo) {
	                        waited += 100;
	                    }
	                }
	            } catch(InterruptedException e) {
	                
	            } finally {
		                startActivity(new Intent(ActSplash.this, ActLogin.class));
		                interrupt();
	            }
	        }
	    };
	    loTreadSplash.start();
	    DalConfiguracion x = new DalConfiguracion(this);
	    x.subDataFramework(getPackageName());
	    
	}
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
        	ibActivo = false;
        }
        return true;
    }
}
