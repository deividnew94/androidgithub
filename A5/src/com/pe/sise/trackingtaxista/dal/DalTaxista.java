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
 		
 		String consulta="Select _id, Dni, Nombres, Email, Contrasena, Telefono, Direccion, ID_Distrito, Foto, Fecha_Registro, ID_Vehiculo, Estado From Taxista Where Email = '" + poBeanTaxista.getEmail() + "' and Password = '" + poBeanTaxista.getContrasena() + "'";
 		Cursor cur = BDTrackingTaxi.rawQuery(consulta, null);
 		
 		Log.v("SQL", consulta);
 		
 		if (cur != null) {
 			if (cur.moveToFirst()) {
 	    	     do {
 	    	    	loBeanTaxista=new BeanTaxista();
 	    	    	Log.v("SQL", cur.getString(1));
 	    	    	loBeanTaxista.setID_Taxista(cur.getString(0));
 	    	    	loBeanTaxista.setDni(cur.getString(1));
 	    	    	loBeanTaxista.setNombre(cur.getString(2));
 	    	    	loBeanTaxista.setEmail(cur.getString(3));
 	    	    	loBeanTaxista.setContrasena(cur.getString(4));
 	    	    	loBeanTaxista.setTelefono(cur.getString(5));
 	    	    	loBeanTaxista.setDireccion(cur.getString(6));
 	    	    	loBeanTaxista.setID_Distrito(cur.getString(7));
 	    	    	loBeanTaxista.setFoto(cur.getString(8));
 	    	    	loBeanTaxista.setFecha_Registro(cur.getString(9));
 	    	    	loBeanTaxista.setID_Vehiculo(cur.getString(10));
 	    	    	loBeanTaxista.setEstado(cur.getString(11));
 	    	    	
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
        loSbSqlCab.append( "INSERT INTO Taxista(Dni, Nombres, Email, Contrasena, Telefono, Direccion, ID_Distrito, Foto, Fecha_Registro, ID_Vehiculo, Estado) VALUES(");
        loSbSqlCab.append("'"); 
        loSbSqlCab.append(inBeanTaxista.getDni()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getNombre()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getEmail()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getContrasena()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getTelefono()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getDireccion()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getID_Distrito()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getFoto()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getFecha_Registro()); //
        loSbSqlCab.append("','");
        loSbSqlCab.append(inBeanTaxista.getID_Vehiculo()); //
        loSbSqlCab.append("','");      
        loSbSqlCab.append(inBeanTaxista.getEstado()); //
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
	
	/*public boolean subUpdate(BeanTaxista inBeanTaxista)
	{
		boolean lbRespuesta=true;
        SQLiteDatabase myDB = null;
        myDB = ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_PRIVATE , null);
        StringBuilder loSbSqlCab= new StringBuilder();
        loSbSqlCab.append( "Update Taxista set Nombres=");
        loSbSqlCab.append("'"); 
        loSbSqlCab.append(inBeanTaxista.getDni()); //
        loSbSqlCab.append("',Dni='");
        loSbSqlCab.append(inBeanTaxista.getNombres()); //
        loSbSqlCab.append("',Nombres='");
        loSbSqlCab.append(inBeanTaxista.getEmail()); //
        loSbSqlCab.append("',Email='");
        loSbSqlCab.append(inBeanTaxista.getContrasena()); //
        loSbSqlCab.append("',Contrasena='");
        loSbSqlCab.append(inBeanTaxista.getTelefono()); //
        loSbSqlCab.append("',Telefono='");
        loSbSqlCab.append(inBeanTaxista.getDireccion()); //
        loSbSqlCab.append("',Direccion='");
        loSbSqlCab.append(inBeanTaxista.getID_Distrito()); //
        loSbSqlCab.append("',ID_Distrito='");        
        
        
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
	}*/
	
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
	            labels.add ( new SpinnerObject ( cursor.getString(0) , cursor.getString(1) ) );
	        } while (cursor.moveToNext());
	    }

	    // closing connection
	    cursor.close();
	    db.close();

	    // returning labels
	    return labels;
	}
	
	public List<SpinnerObject> getAllLabelDistrito(){
	    List<SpinnerObject> labels = new ArrayList<SpinnerObject>();
	    // Select All Query
	    String selectQuery = "SELECT * FROM DISTRITO; ";

	    SQLiteDatabase db = null;
 		db= ioContext.openOrCreateDatabase("bd_TrackingTaxi", Context.MODE_WORLD_READABLE, null);
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if ( cursor.moveToFirst () ) {
	        do {
	            labels.add ( new SpinnerObject ( cursor.getString(0) , cursor.getString(1) ) );
	        } while (cursor.moveToNext());
	    }

	    // closing connection
	    cursor.close();
	    db.close();

	    // returning labels
	    return labels;
	}
	
}
