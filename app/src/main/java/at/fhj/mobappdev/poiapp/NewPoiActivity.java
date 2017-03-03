package at.fhj.mobappdev.poiapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NewPoiActivity extends AppCompatActivity {

    EditText edtAddress;

    EditText edtCoords;
    private LocationManager locManager;
    private LocListener loclistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poi);


        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtCoords = (EditText) findViewById(R.id.edtCoords);


        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        loclistener = new LocListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

            // ask for permmission -> opens a system dialog

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
        else{
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, loclistener);
        }



    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode==123){

            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, loclistener);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        // remove if not active
        locManager.removeUpdates(loclistener);
    }






    // private locstion listener
    private class LocListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

                NewPoiActivity.this.edtCoords.setText(location.getLatitude() +", "+ location.getLongitude());
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
}
