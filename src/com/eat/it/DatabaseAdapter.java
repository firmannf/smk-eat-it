package com.eat.it;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {
	//Declare variable
	public static final String Row_id = "_id";
	public static final String Row_resto = "resto";
	public static final String Row_loc = "location";
	public static final String Row_phone = "phone";
	public static final String Row_variant = "variant";
	public static final String Row_menu = "menu";
	public static final String Row_price = "price";
	public static final String Row_lat = "latitude";
	public static final String Row_long = "longitude";
	public static final String Row_fav = "fav";
	public static final String Row_photo = "photo";
	
	private static final String DATABASE_NAME = "Eait_database_fix";
	private static final String TABLE_NAME = "tableresto";
	private static final int DATABASE_VERSION = 1;
	
	private static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("+Row_id+" integer PRIMARY KEY, "+Row_resto+" text, "+Row_loc+" text, "+Row_phone+" text, "+Row_variant+" text, "+Row_menu+" text, "+Row_price+" text, "+Row_lat+" text, "+Row_long+" text, "+Row_fav+" integer, "+Row_photo+" integer)";
	private final Context ctx;
	private DatabaseOpenHelper dbHelper;
	private static SQLiteDatabase db;
	
	//constructor
	public DatabaseAdapter(Context context){
		this.ctx = context;
	}
	
	public DatabaseAdapter open() throws SQLException{
		dbHelper= new DatabaseOpenHelper(ctx);
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public boolean deleteAllData() {
		  int doneDelete = 0;
		  doneDelete = db.delete(TABLE_NAME, null , null);
		  return doneDelete > 0;
	}
	
	public long fav(int a){
		//db.execSQL("UPDATE "+TABLE_NAME+" SET "+Row_fav+"=1 WHERE "+Row_resto+"='"+a+"'");
		ContentValues cv = new ContentValues();
		cv.put(Row_fav, 1);
		return db.update(TABLE_NAME, cv, Row_id+"='"+a+"'", null);
	}
	
	public long unfav(int a){
		ContentValues cv = new ContentValues();
		cv.put(Row_fav, 0);
		return db.update(TABLE_NAME, cv, Row_id+"='"+a+"'", null);
	}
	
	public Cursor fetchAllData() {	 
		  Cursor mCursor = db.query(TABLE_NAME, new String[] {Row_id, Row_resto, Row_loc, Row_phone, Row_variant, Row_menu, Row_price, Row_lat, Row_long, Row_fav, Row_photo},
		    null, null, null, null, null);
		
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  
		  return mCursor;
	}
	
	public Cursor FetchbyFav(){
		Cursor mCursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+Row_fav+"=1", null);
		
		if (mCursor != null) {
			   mCursor.moveToFirst();
			  }
			  
		return mCursor;
	}
	
	private static class DatabaseOpenHelper extends SQLiteOpenHelper{

		DatabaseOpenHelper(final Context context) {
	        super(context,DATABASE_NAME,null,DATABASE_VERSION);
	    }
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE);//create table
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('1','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0, "+R.drawable.rajasunda+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('2','Kampung Daun', 'Jl. Sersan Bajuri KM 4.7 No.88 RR 1, Cibeureum', '(022) 2787915', 'Sunda','Nasi Timbel Komplit, Sop Buntut, Sop Gurame, Sop Iga Sapi', 'Rp. 50.001 - Rp. 100.000', '-6.814455', '107.5903', 0, "+R.drawable.kampungdaun+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('3','Sapu Lidi', ' Jl. Sersan Bajuri, Lembang', ' (022) 2786915', 'Sunda','Ayam Bumbu Warisan, Sop Buntut', ' Rp. 50.001 - Rp. 100.000', '-6.848373', '107.593178', 0, "+R.drawable.sapulidi+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('4','Nasi Bancakan ', ' Jl. Trunojoyo No. 62, Ir. Haji Juanda ', ' (022) 4203650', 'Sunda','Ayam Goreng/Bakar Cisaga, Gepuk, Ikan Mas Goreng, Pindang Bandeng, Sambal Goreng Daging, Teri Kacang', 'Di bawah Rp.30.000', '-6.902526 ', '107.612454', 0, "+R.drawable.nasibancakan+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('5','Raja Sunda', ' Jl. Terusan Pasteur, Cipaganti', '(022) 6037688', 'Sunda','Ikan Bakar, Timbel Super Top', 'Rp. 30.001 - Rp. 50.000', '-6.869166', '107.583404', 0, "+R.drawable.rajasunda+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('6','De Ranch', ' Jl. Maribaya No. 17, Lembang', '-', 'Sunda','Ayam Goreng, Nasi Goreng', 'Rp. 30.001 - Rp. 50.000', '-6.823872', '107.640775', 0, "+R.drawable.deranch+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('7','Surabi Imut', 'Jl. Doktor Setiabudi No. 194, Setiabudi', ' (022) 71291716', 'Sunda','Nasi Goreng, Surabi', 'Di bawah Rp.30.000', '-6.86563', '107.593829', 0, "+R.drawable.surabiimut+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('8','Batagor Riri', 'Jl. Burangrang No. 41, Burangrang', '(022) 7303349', 'Sunda','Batagor', 'Rp. 30.001 - Rp. 50.000', '-6.925064', '107.618881', 0, "+R.drawable.batagorriri+")");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('9','Surabi Enhaii', 'Jl. Setiabudi No. 186, Setiabudi', '(022) 76966333', 'Sunda','Surabi Special', 'Di bawah Rp.30.000', '-6.869294', '107.593721', 0, "+R.drawable.surabienhaii+")");
			/*db.execSQL("INSERT INTO "+TABLE_NAME+" values('10','Mie Kocok Kaki Sapi Kebon Jukut', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('11','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('12','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('13','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('14','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('15','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('16','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('17','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('18','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('19','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('20','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('21','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('22','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('23','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('24','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('25','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('26','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('27','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('28','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('29','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('30','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");
			db.execSQL("INSERT INTO "+TABLE_NAME+" values('31','Raja rasa', 'Jl. Setra Ria No.1 (Depan Mal Setra Sari), Asia Afrika', '(022) 2005070', 'Sunda','-', 'Rp. 30.001 - Rp. 50.000', '-6.876665', '107.58445', 0)");*/
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
			onCreate(db); 	
		}		
	}
}