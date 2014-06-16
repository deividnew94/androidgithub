package com.pe.sise.trackingtaxista.dal;

import java.util.ArrayList;
import java.util.List;

import com.pe.sise.trackingtaxista.bean.BeanTaxista;
import com.pe.sise.trackingtaxista.bean.SpinnerObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DalTaxista {
	
	private Context ioContext;
	
	public DalTaxista(Context clie){
		ioContext = clie;
	}

	public BeanTaxista subReading(BeanTaxista poBeanTaxista){
		BeanTaxista loBeanTaxista=null;
 		SQLiteDatabase BDTrackingTaxi = null;
 		BDTrackingTaxi = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_WORLD_READABLE, null); 
 		
 		String consulta="Select _id, nombres, apellidos, usuario, password, email, vehiculoid From Taxista Where usuario = '" + poBeanTaxista.getUsuario() + "' and password = '" + poBeanTaxista.getPassword() + "'";
 		Cursor cur = BDTrackingTaxi.rawQuery(consulta, null);
 		
 		Log.v("SQL", consulta);
 		
 		if (cur != null) {
 			if (cur.moveToFirst()) {
 	    	     do {
 	    	    	loBeanTaxista=new BeanTaxista();
 	    	    	Log.v("SQL", cur.getString(1));
 	    	    	loBeanTaxista.setIdTaxista(cur.getInt(0));
 	    	    	loBeanTaxista.setNombres(cur.getString(1));
 	    	    	loBeanTaxista.setApellidos(cur.getString(2));
 	    	    	loBeanTaxista.setUsuario(cur.getString(3));
 	    	    	loBeanTaxista.setPassword(cur.getString(4));
 	    	    	loBeanTaxista.setEmail(cur.getString(5));
 	    	    	loBeanTaxista.setVehiculoId(cur.getInt(6));
 	    	     } while(cur.moveToNext());
 	    	} 
         }
 		cur.deactivate();
 		cur.close();
 		BDTrackingTaxi.close();
 		return loBeanTaxista;
	}
	
	public boolean subInsert(BeanTaxista inBeanTaxista)
	{
		boolean lbRespuesta=true;
        SQLiteDatabase myDB = null;
        myDB = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_PRIVATE , null);
        StringBuilder loSbSqlCab= new StringBuilder();
        loSbSqlCab.append( "INSERT INTO Taxista(nombres, apellidos, usuario, password, email, vehiculoid) VALUES(");
        loSbSqlCab.append("'"); 
        loSbSqlCab.append(inBeanTaxista.getNombres()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getApellidos()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getUsuario()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getPassword()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getEmail()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getVehiculoId()); //
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
	
	public boolean subUpdate(BeanTaxista inBeanTaxista)
	{
		boolean lbRespuesta=true;
        SQLiteDatabase myDB = null;
        myDB = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_PRIVATE , null);
        StringBuilder loSbSqlCab= new StringBuilder();
        loSbSqlCab.append( "Update Taxista set Nombres=");
        loSbSqlCab.append("'"); 
        loSbSqlCab.append(inBeanTaxista.getNombres()); //
        loSbSqlCab.append("',Apellidos='");
        loSbSqlCab.append(inBeanTaxista.getApellidos()); //
        loSbSqlCab.append("',Usuario='");
        loSbSqlCab.append(inBeanTaxista.getUsuario()); //
        loSbSqlCab.append("',Password='");
        loSbSqlCab.append(inBeanTaxista.getPassword()); //
        loSbSqlCab.append("',Email='");
        loSbSqlCab.append(inBeanTaxista.getEmail()); //
        loSbSqlCab.append("',VehiculoId='");
        loSbSqlCab.append(inBeanTaxista.getVehiculoId()); //
        loSbSqlCab.append("' where _id=");
        loSbSqlCab.append(inBeanTaxista.getIdTaxista());
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
            loSbSqlCab.append( "Delete Taxista Where _id=");
            loSbSqlCab.append(campoID);
            
            try {
                myDB.execSQL(loSbSqlCab.toString());
         } catch (Exception e) {
                resultado= false;
         }
         myDB.close();        
    	 return resultado;
    }
	
	public List<SpinnerObject> getAllLabels(){
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
	}
	
}
