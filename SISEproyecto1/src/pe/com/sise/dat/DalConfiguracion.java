package pe.com.sise.dat;

import com.android.dataframework.DataFramework;

import android.content.Context;
import android.database.Cursor;

public class DalConfiguracion {

	private Context ioContext;
	public DalConfiguracion(Context poContext)
	{
		ioContext=poContext;
	}
	public void subDataFramework(String psPaquete)
	{
		try {
        	DataFramework.getInstance().open(ioContext, psPaquete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
