package pe.com.sise.dat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DalCFCorrelativo {
	private Context ioContext;
	public DalCFCorrelativo(Context psClase)
	{
		
		ioContext=psClase;
	}
	public void subLeer(String psFiltro)
	{
 		SQLiteDatabase myDB = null;
 		myDB = ioContext.openOrCreateDatabase("db_sise", Context.MODE_WORLD_READABLE, null);
		String lsQuery="select idUsuario,fecha, ultNumProforma,ultNumPedido from CFCorrelativo where idUsuario<'" + psFiltro + "'";
 		Cursor loCursor = myDB.rawQuery(lsQuery, null);
 		Log.v("SQL", lsQuery);
 		if (loCursor != null) {
 			if (loCursor.moveToFirst()) {
 	    	     do {
 	    	    	 Log.v("SQL", String.valueOf(loCursor.getInt(0)));
 	    	    		    	    	 
 	    	     } while(loCursor.moveToNext());
 	    	} 
         }
 		loCursor.deactivate();
 		loCursor.close();
 		myDB.close();
 		
	}
}
