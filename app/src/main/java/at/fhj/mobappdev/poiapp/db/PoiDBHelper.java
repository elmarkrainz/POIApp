package at.fhj.mobappdev.poiapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DB Helper for Sqlite Db generation
 *
 * @author EKrainz
 */
public class PoiDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "POI.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_POIS = "Pois";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LONG = "long";
    public static final String COLUMN_ADDRESS = "address";


    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_POIS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_LAT + " real, "
            + COLUMN_LONG + " real, "
            + COLUMN_ADDRESS + " text);";


    public PoiDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create DB
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // upgrade DB
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POIS);
        onCreate(db);
    }

}
