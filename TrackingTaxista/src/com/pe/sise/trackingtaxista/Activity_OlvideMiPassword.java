package com.pe.sise.trackingtaxista;

import greendroid.app.GDActivity;
import android.os.Bundle;
import android.view.Menu;

public class Activity_OlvideMiPassword extends GDActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.layout_olvidemipassword);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.layout_olvidemipassword, menu);
		return true;
	}

}
