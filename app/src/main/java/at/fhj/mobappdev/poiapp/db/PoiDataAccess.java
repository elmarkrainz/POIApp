package at.fhj.mobappdev.poiapp.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * DataAccess Layer to simply Insert, GetAll, Delete
 *
 * @author EKrainz
 */
public class PoiDataAccess {

	private PoiDBHelper poiHelper;
	private SQLiteDatabase database;

	public PoiDataAccess(Context c) {
		poiHelper = new PoiDBHelper(c);
	}

	// public void open() throws SQLException {
	// database = poiHelper.getWritableDatabase();
	// }
	//
	// public void close() {
	// poiHelper.close();
	// }

	public long addPOI(PoiObject poi) {
		// 1. open DB

		database = poiHelper.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(PoiDBHelper.COLUMN_NAME, poi.getName()); // add name
		values.put(PoiDBHelper.COLUMN_LAT, poi.getLatitude());
		values.put(PoiDBHelper.COLUMN_LONG, poi.getLongitude());
		values.put(PoiDBHelper.COLUMN_ADDRESS, poi.getAddress());

		// 3. insert
		long id =database.insert(PoiDBHelper.TABLE_POIS, // table
				null, // nullColumnHack
				values); // key/value -> keys = column names/ values = column
							// values

		// 4. close
		database.close();


		return id;
	}

	public void	deletePOI(PoiObject poi) {

		database = poiHelper.getWritableDatabase();
		database.delete(PoiDBHelper.TABLE_POIS,PoiDBHelper.COLUMN_ID + "=" + poi.getId(),null);
		database.close();

	}

	public List<PoiObject> getAllPois() {

		List<PoiObject> pois = new ArrayList<PoiObject>();

		//build the query
		String query = "SELECT  * FROM " + poiHelper.TABLE_POIS;
		database = poiHelper.getWritableDatabase();

		Cursor cursor = database.rawQuery(query, null);

		PoiObject tmpPoi = new PoiObject();

		if (cursor.moveToFirst()) {
			do {
				tmpPoi.setId(cursor.getLong(0));
				tmpPoi.setName(cursor.getString(1));
				tmpPoi.setLatitude(cursor.getDouble(2));
				tmpPoi.setLongitude(cursor.getDouble(3));
				tmpPoi.setAddress(cursor.getString(4));

				// Add poi to pois
				pois.add(tmpPoi);
			} while (cursor.moveToNext());
		}
		return pois;
	}

}
