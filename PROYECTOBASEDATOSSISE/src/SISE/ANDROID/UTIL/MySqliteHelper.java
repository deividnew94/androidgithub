package SISE.ANDROID.UTIL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static android.provider.BaseColumns._ID;

public class MySqliteHelper extends SQLiteOpenHelper {

	public static final String NOMBRETABLA="persona";
	private static final String NOMBREBASEDATOS="BD.db";
	private static final int VERSION=1;
	private static final String SQL="create table "+NOMBRETABLA+
	" (codigo integer primary key autoincrement ,"+
	" nombre text not null, apellido text not null, " +
	" dni text not null );";
	
	public MySqliteHelper(Context contexto) 
	{
		super(contexto, NOMBREBASEDATOS, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int antiguaversion, int nuevaversion) {
		Log.w(MySqliteHelper.class.getName(),
		"Actualizando la versión de la Base de Datos Numero :"+
		antiguaversion+ " a "+nuevaversion);
		db.execSQL("DROP TABLE IF EXISTS "+NOMBRETABLA);
		onCreate(db);
	}


	
	
	
}
