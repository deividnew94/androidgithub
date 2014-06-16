package com.pe.sise.trackingtaxi.dal;

import com.android.dataframework.DataFramework;

import android.content.Context;
//import android.database.Cursor;

public class DalConfiguracion {

	private Context ioContext;
	public DalConfiguracion(Context poContext)
	{
		ioContext=poContext;
	}
	public void subDataFramework(String pack)
	{
		try {
        	DataFramework.getInstance().open(ioContext, pack);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
