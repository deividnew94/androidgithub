package SISE.ANDROID.DAO;

import SISE.ANDROID.BEAN.Persona;
import SISE.ANDROID.UTIL.MySqliteHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;

public class PersonaDAO 
{
	private SQLiteDatabase database;
	private MySqliteHelper dbHelper;  
	
	public PersonaDAO(Context contexto)
	{
		dbHelper=new MySqliteHelper(contexto);
	}
	
	public void open()throws SQLException
	{
		database=dbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public long Insertar(Persona objPersona)
	{
		long estado=0;
		try
		{
			ContentValues valores=new ContentValues();
			valores.put("nombre",objPersona.getNombre());
			valores.put("apellido",objPersona.getApellido());
			valores.put("dni",objPersona.getDni());
			estado=database.insert(MySqliteHelper.NOMBRETABLA,null,valores);
		}catch (Exception e)
		{
			estado=0;
		}		
	return estado;	
	}	
	
	public ArrayList<Persona> ListadoGeneral()
	{	Cursor c;
		ArrayList<Persona> lista=new ArrayList<Persona>();
		try
		{
			c=database.rawQuery("SELECT * FROM persona", null);
			while(c.moveToNext())
			{
				Persona objPersona=new Persona();
				objPersona.setCodigo(c.getInt(0));
				objPersona.setNombre(c.getString(1));
				objPersona.setApellido(c.getString(2));
				objPersona.setDni(c.getString(3));
				lista.add(objPersona);
			}
		c.close();	
		}catch (Exception e)
		{
		}
		return lista;
	}
	
}
