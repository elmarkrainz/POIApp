package at.fhj.mobappdev.poiapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.fhj.mobappdev.poiapp.db.PoiDataAccess;
import at.fhj.mobappdev.poiapp.db.PoiObject;

/**
 * starting GPS updates
 *  - Locationmanager & Listener
 *  - Calling an Asynctask for Geocoding
 *  - saving POI to DB
 *
 * @author EKrainz
 */
public class NewPoiActivity extends AppCompatActivity {

    private static final int REQUEST_GPS = 101;
    private EditText edtAddress;
    private EditText edtCoords;

    private CheckBox chkGps;
    private LocationManager locManager;
    private LocListener loclistener;
    private double latitude;
    private double longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poi);

        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtCoords = (EditText) findViewById(R.id.edtCoords);
        chkGps = (CheckBox) findViewById(R.id.chkGps);
        chkGps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkGps.isChecked()) {
                    startGPS();
                } else {
                    stopGPS();
                }
            }
        });


        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        loclistener = new LocListener();

    }


    @Override
    protected void onPause() {
        super.onPause();
        stopGPS();
    }


    private void stopGPS() {
        Log.i("GPS", "stop Gps ");
        locManager.removeUpdates(loclistener);
    }

    private void startGPS() {

        Log.i("GPS", "start Gps ");

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // ask for permmission -> opens a system dialog
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_GPS);
        } else {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, loclistener);
        }

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_GPS) {
            startGPS();
        }
    }


    public void geocode(View v) {

       getAddress();

    }

    private void getAddress(){
        String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

        if (!edtCoords.getText().equals("")) {
            url+=latitude+","+longitude  +"&sensor=true";
        }else{
            url+="47.4533,15.3319&sensor=true";  //some defaults
        }

        HttpHelper httpHelper = new HttpHelper();
        httpHelper.setCallback(new AsynCallback());
        httpHelper.execute(url);
    }



    public void savePoi(View v) {


        PoiObject poi = new PoiObject();
        poi.setName("Demo");  // add textfield for name

        poi.setLongitude(longitude);
        poi.setLatitude(latitude);
        poi.setAddress(edtAddress.getText().toString());


        PoiDataAccess poiDataAccess = new PoiDataAccess(this);

        Long id = poiDataAccess.addPOI(poi);
        Log.i("DB OP", "poi added to db, id:"+ id);



    }


    // private locationlistener class

    private class LocListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            NewPoiActivity.this.latitude = location.getLatitude();
            NewPoiActivity.this.longitude = location.getLongitude();

            NewPoiActivity.this.edtCoords.setText("lat.: " + location.getLatitude() + "\nlong.:" + location.getLongitude());

            getAddress();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    // private callback class
    private class AsynCallback implements ICallback {

        @Override
        public void handleResult(String s) {

            // NewPoiActivity.this.edtAddress.setText(s);

            try {
                JSONObject jsonObj = new JSONObject(s);

                JSONArray results = jsonObj.getJSONArray("results");
                JSONObject jo = results.getJSONObject(0);


                String address = jo.getString("formatted_address");

                NewPoiActivity.this.edtAddress.setText(address);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
