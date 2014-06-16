package com.pe.sise.trackingtaxista;

import greendroid.app.GDApplication;


public class Aplicacion extends GDApplication {
	@Override
	public Class<?> getHomeActivityClass() {
		// TODO Auto-generated method stub
		// Éste return muestra la actividad que desees (act_menu, act_login, etc) al pulsar el boton home del emulador (casita)
		return Activity_Login.class ;
		//return.super.getHomeActivityClass()
	}
}
