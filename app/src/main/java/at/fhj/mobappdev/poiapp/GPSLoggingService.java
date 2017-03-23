package at.fhj.mobappdev.poiapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * example of a Logging Service -
 * Service to Log GPS Data and save it to an internal File
 *
 *
 *
 * @author EKrainz
 */
public class GPSLoggingService extends Service {

    private LocationManager locManager;
    private LocationListener locListener;
    private StringBuffer logBuffer;

    public GPSLoggingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //doSomeStuff();
        geoLogging();
    }


    private void doSomeStuff() {

       // geoLogging();
        Log.i("SERVICE", "Service do something ");

        int counter = 0;

        while (counter < 5) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Toast.makeText(GPSLoggingService.this, "Service is running: " + counter, Toast.LENGTH_LONG)
                    .show();

            counter++;

        }
        stopSelf();
    }


    private void geoLogging() {

        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locListener = new LoggingLocationListerner();
        logBuffer = new StringBuffer();

        // no request for permission !!
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locListener);

        writeToFile();
    }


    private void writeToFile() {
        String filename = "gpslog" + ".txt";

        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(logBuffer.toString());
            osw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SERVICE", "Service stopped clean up ");

        // clean up
        locManager.removeUpdates(locListener);
        writeToFile();

        Log.i("SERVICE", "Service stopped  ");

    }


    private class LoggingLocationListerner implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.i("GPS LOGGING SERVICE", "loc changed");
            logBuffer.append(location.getLatitude());
            logBuffer.append(", ");
            logBuffer.append(location.getLongitude());
            logBuffer.append("\n");

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

