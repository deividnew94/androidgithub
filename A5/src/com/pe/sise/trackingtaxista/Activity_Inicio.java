package com.pe.sise.trackingtaxista;

import greendroid.app.GDActivity;
import android.app.TabActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.widget.TabHost;

public class Activity_Inicio extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.layout_inicio);
		//TabHost tabHost = getTabHost();
		//tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Dialer").setContent(R.id.tab1));
		//tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Hola").setContent(R.id.tab2));
		//tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Adios").setContent(R.id.tab3));
		addPreferencesFromResource(R.xml.mis_preferencias);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.layout_inicio, menu);
		return true;
	}

}
