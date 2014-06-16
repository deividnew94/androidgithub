package com.pe.sise.trackingtaxi.dal;

//import java.util.ArrayList;
//import java.util.List;

import com.pe.sise.trackingtaxi.bean.BeanCliente;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DalCliente {
	
	private Context ioContext;
	
	public DalCliente(Context clie){
		ioContext = clie;
	}

	public BeanCliente subReading(BeanCliente poBeanCliente){
		BeanCliente loBeanCliente=null;
 		SQLiteDatabase BDTrackingTaxi = null;
 		BDTrackingTaxi = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_WORLD_READABLE, null); 
 		
 		String consulta="Select _id, nombres, telefono, email, password From Cliente Where email = '" + poBeanCliente.getEmail() + "' and password = '" + poBeanCliente.getContrasena() + "'";
 		Cursor cur = BDTrackingTaxi.rawQuery(consulta, null);
 		
 		Log.v("SQL", consulta);
 		
 		if (cur != null) {
 			if (cur.moveToFirst()) {
 	    	     do {
 	    	    	loBeanCliente=new BeanCliente();
 	    	    	Log.v("SQL", cur.getString(1));
 	    	    	loBeanCliente.setIdCliente(cur.getInt(0));
 	    	    	loBeanCliente.setNombres(cur.getString(1));
 	    	    	loBeanCliente.setTelefono(cur.getString(2));
 	    	    	loBeanCliente.setEmail(cur.getString(3));
 	    	    	loBeanCliente.setContrasena(cur.getString(4));
 	    	     } while(cur.moveToNext());
 	    	} 
         }
 		cur.deactivate();
 		cur.close();
 		BDTrackingTaxi.close();
 		return loBeanCliente;
	}
	
	public boolean subInsert(BeanCliente inBeanTaxista)
	{
		boolean lbRespuesta=true;
        SQLiteDatabase myDB = null;
        myDB = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_PRIVATE , null);
        StringBuilder loSbSqlCab= new StringBuilder();
        loSbSqlCab.append( "INSERT INTO Cliente(nombres, telefono, email, password) VALUES(");
        loSbSqlCab.append("'"); 
        loSbSqlCab.append(inBeanTaxista.getNombres()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getTelefono()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getEmail()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getContrasena()); //
        loSbSqlCab.append("')");
        Log. v("SQL", "insert="+loSbSqlCab.toString());
        
         try {
               myDB.execSQL(loSbSqlCab.toString());
        } catch (Exception e) {
               lbRespuesta= false;
        }
        myDB.close();
         return lbRespuesta;		
	}
	
	public boolean subUpdate(BeanCliente inBeanTaxista)
	{
		boolean lbRespuesta=true;
        SQLiteDatabase myDB = null;
        myDB = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_PRIVATE , null);
        StringBuilder loSbSqlCab= new StringBuilder();
        loSbSqlCab.append( "Update Cliente set Nombres=");
        loSbSqlCab.append("'"); 
        loSbSqlCab.append(inBeanTaxista.getNombres()); //
        loSbSqlCab.append("',Nombres='");
        loSbSqlCab.append(inBeanTaxista.getTelefono()); //
        loSbSqlCab.append("',Telefono='");
        loSbSqlCab.append(inBeanTaxista.getEmail()); //
        loSbSqlCab.append("',Email='");
        loSbSqlCab.append(inBeanTaxista.getContrasena()); //
        loSbSqlCab.append("' Password='=");
        loSbSqlCab.append(inBeanTaxista.getIdCliente());
        Log. v("SQL", "update="+loSbSqlCab.toString());
        
         try {
               myDB.execSQL(loSbSqlCab.toString());
        } catch (Exception e) {
               lbRespuesta= false;
        }
        myDB.close();
         return lbRespuesta;		
	}
	
	public boolean eliminarRegistrosPorID(String campoID){
    	boolean resultado=true;
    		SQLiteDatabase myDB = null;
            myDB = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_PRIVATE , null);
            StringBuilder loSbSqlCab= new StringBuilder();
            loSbSqlCab.append( "Delete Cliente Where _id=");
            loSbSqlCab.append(campoID);
            
            try {
                myDB.execSQL(loSbSqlCab.toString());
         } catch (Exception e) {
                resultado= false;
         }
         myDB.close();        
    	 return resultado;
    }
	
	/*public List<SpinnerObject> getAllLabels(){
	    List<SpinnerObject> labels = new ArrayList<SpinnerObject>();
	    // Select All Query
	    String selectQuery = "SELECT * FROM VEHICULO; ";

	    SQLiteDatabase db = null;
 		db= ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_WORLD_READABLE, null);
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if ( cursor.moveToFirst () ) {
	        do {
	            labels.add ( new SpinnerObject ( cursor.getInt(0) , cursor.getString(1) ) );
	        } while (cursor.moveToNext());
	    }

	    // closing connection
	    cursor.close();
	    db.close();

	    // returning labels
	    return labels;
	}*/
	
}
