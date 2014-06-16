package com.pe.sise.trackingtaxista.dal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONObject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;
public class BDFramework {
		
		private String DB_NOMBRE="DB.FRAMEWORK";
	    private int DB_VERSION=1;
	    private String DB_LAST_ERROR="";
	    
	    private SQLiteDatabase db;
	    private Cursor cursor;
	    
	    private final Context context;
	    private DatabaseHelper DBHelper;
	    
	    public boolean log=false;
	    
	    public SQLiteDatabase getInstance(){
	    	if(db==null){
	    		open();
	    	}
	    	return db;
	    }
	    
	    public BDFramework(Context ctx){
	    	this.context=ctx;
	    }
	    
	    private static class DatabaseHelper extends SQLiteOpenHelper 
	    {
	        DatabaseHelper(Context context,String nombre,int version) 
	        {
	            super(context, nombre, null, version);
	        }

	        @Override
	        public void onCreate(SQLiteDatabase db) 
	        {
	            /*db.execSQL(TABLA_PRUEBA);*/
	        }

	        @Override
	        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
	                              int newVersion) 
	        {
	            /*Log.w("LOG_VERSION", "Verificando version vieja" + oldVersion 
	                  + " nueva " + newVersion );
	            db.execSQL(DROP_TABLA_PRUEBA);
	            onCreate(db);*/
	        }
	    }
	    
	    public BDFramework open() throws SQLException 
	    {
	    	if(DBHelper==null){
	    		DBHelper = new DatabaseHelper(context,DB_NOMBRE,DB_VERSION);
	    	}
	        db = DBHelper.getWritableDatabase();
	        return this;
	    }

	   public void close() 
	    {
	        DBHelper.close();
	    }
	   
	   public boolean verificaExistencia(){
		   boolean resultado=false;
		   if(db==null){
			   open();
			   resultado=true;
		   }else{
			   resultado=true;
		   }
		   return resultado;
	   }
	    
	    
	    public boolean ejecutarSQL(String query){
	    	boolean resultado=false;
	    	try {
	    		log(query);
	    		if(verificaExistencia()){
		    		if(db.isOpen()){
		    			db.execSQL(query);
		    		}
		    		resultado=true;
	    		}
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			}
			return resultado;
	    }
	    
	    public boolean ejecutarSQL(String query,String[] args){
	    	boolean resultado=false;
	    	try {
	    		log(query);
	    		if(verificaExistencia()){
		    		if(db.isOpen()){
		    			db.execSQL(query,args);
		    		}
		    		resultado=true;
	    		}
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			}
			return resultado;
	    }
	    
	    public Cursor ejecutarQuery(String query,String[] args){
	     	try {
	     		log(query);
	     		if(verificaExistencia()){
		    		if(db.isOpen()){
		    			cursor=db.rawQuery(query,args);
		    		}
	     		}
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
				cursor=null;
			}
			return cursor;
	    }
	    
	    public Cursor ejecutarQuery(String query){
	     	try {
	     		log(query);
	     		if(verificaExistencia()){
		    		if(db.isOpen()){
		    			cursor=db.rawQuery(query,null);
		    		}
	     		}
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
				cursor=null;
			}
			return cursor;
	    }
	    
	    public long insertRegistro(String nombreTabla,String tipo[],String columnas[],String valores[]) throws Exception{
	    	long resultado=0;
	    	try {
	    		ContentValues insert = new ContentValues();
				for (int i = 0; i < columnas.length; i++) {
					if(tipo[i].toString().toUpperCase().equals("STRING")){
						insert.put(columnas[i], valores[i]);
					}
					else if(tipo[i].toString().toUpperCase().equals("INT")){
						insert.put(columnas[i], Integer.parseInt(valores[i]));
					}
					else if(tipo[i].toString().toUpperCase().equals("FLOAT")){
						insert.put(columnas[i], Float.parseFloat(valores[i]));
					}
				}
				resultado=db.insertOrThrow(nombreTabla, null, insert);
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
				throw e;
			}
	    	return resultado;
	    }
	    
	    public boolean actualizarRegistrosPorID(String nombreTabla,String campoID,long idUpdate,String tipo[],String columnas[],String valores[]){
	    	boolean resultado=false;
	    	try {
	    		ContentValues update = new ContentValues();
				for (int i = 0; i < columnas.length; i++) {
					if(tipo[i].toString().toUpperCase().equals("STRING")){
						update.put(columnas[i], valores[i]);
					}
					else if(tipo[i].toString().toUpperCase().equals("INT")){
						update.put(columnas[i], Integer.parseInt(valores[i]));
					}
					else if(tipo[i].toString().toUpperCase().equals("FLOAT")){
						update.put(columnas[i], Float.parseFloat(valores[i]));
					}
				}
				db.update(nombreTabla, update, campoID, new String[] {Long.toString(idUpdate)});
				resultado=true;
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			}
	    	return resultado;
	    }
	    
	    public boolean eliminarRegistrosPorID(String nombreTabla,String campoID,long idDelete){
	    	boolean resultado=false;
	    	try {
	    		db.delete(nombreTabla, campoID, new String[] {Long.toString(idDelete)});
				resultado=true;
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			}
	    	return resultado;
	    }
	    
	    public boolean insertBatch(String sql,String separador){
	    	boolean resultado=false;

	    	try {
	    		db.beginTransaction();
	    		StringTokenizer tokens = new StringTokenizer(sql,separador);
	    		String sqlComand;
	    		while (tokens.hasMoreElements()) {
					sqlComand = (String) tokens.nextElement();
					db.execSQL(sqlComand);
				}
	    		
	    		db.setTransactionSuccessful();
	    		resultado=true;
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			}finally{
				db.endTransaction();
			}
	    	
	    	return resultado;
	    }
	    
	    //..........................................................
	    public boolean insertBatch(File file) {
			boolean resultado = false;
			try {
				db.beginTransaction();
				String sqlComand;
				Log.i("Ok","Metiendo en DB");
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((sqlComand = br.readLine()) != null) {
					//System.out.println(sqlComand);
					db.execSQL(sqlComand);
				}
				br.close();
				Log.i("Ok","Termino DB");
				db.setTransactionSuccessful();
				resultado = true;
			} catch (IOException e) {

			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			} finally {
				db.endTransaction();
				db.close();
			}

			return resultado;
		}

	    
	    //..........................................................
	    
	    public boolean insertBatch(JSONArray jArray,String parametro){
	    	Log.w("crearBDeInsertar","insertBatch");
	    	boolean resultado=false;
	    	String sqlComand="";
	    	JSONObject jObject=new JSONObject();
	    	try {
	    		db.beginTransaction();
	    		for (int i = 0; i < jArray.length(); i++) 
				{
	    			jObject= jArray.getJSONObject(i);
	    			sqlComand = jObject.get("sql").toString();
	    			//Log.w("crearBDeInsertar","sqlComand:"+sqlComand);
					db.execSQL(sqlComand);
				}
	    		
	    		db.setTransactionSuccessful();
	    		resultado=true;
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			}finally{
				db.endTransaction();
			}
			Log.w("crearBDeInsertar","insertBatch resultado:"+resultado);
			
			db.close();
	    	return resultado;
	    }
	    
	    public boolean insertBatch(List<String> lista){
	    	Log.w("crearBDeInsertar","insertBatch");
	    	boolean resultado=false;
	    	String sqlComand="";
	    	try {
	    		db.beginTransaction();
	    		for (int i = 0; i < lista.size(); i++) 
				{
	    			//Log.w("crearBDeInsertar","sqlComand:"+sqlComand);
	    			sqlComand = (String) lista.get(i);
					db.execSQL(sqlComand);
				}
	    		
	    		db.setTransactionSuccessful();
	    		resultado=true;
			} catch (Exception e) {
				setDB_LAST_ERROR(e.getMessage());
			}finally{
				db.endTransaction();
			}
			Log.w("crearBDeInsertar","insertBatch resultado:"+resultado);
	    	return resultado;
	    }
	    
	    private void log(String cadena){
	    	if(log){
	    		Log.println(1, "BDFramework", cadena);
	    	}
	    	
	    }
		
		private void setDB_LAST_ERROR(String dB_LAST_ERROR) {
			DB_LAST_ERROR = dB_LAST_ERROR;
		}

		public String getDB_LAST_ERROR() {
			return DB_LAST_ERROR;
		}
		
		public String getDB_NOMBRE() {
			return DB_NOMBRE;
		}

		public void setDB_NOMBRE(String dB_NOMBRE) {
			DB_NOMBRE = dB_NOMBRE;
		}
		
		public int getDB_VERSION() {
			return DB_VERSION;
		}

		public void setDB_VERSION(int dB_VERSION) {
			DB_VERSION = dB_VERSION;
		}

	}

