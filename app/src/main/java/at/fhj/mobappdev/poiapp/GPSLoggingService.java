package at.fhj.mobappdev.poiapp;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GPSLoggingService extends Service {


    public GPSLoggingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        doSomeStuff();
    }


    private void doSomeStuff() {


        Log.i("SERVICE", "Service do something ");

        int counter = 0;

        while (counter < 5) {

            Toast.makeText(GPSLoggingService.this, "Service is running: " + counter, Toast.LENGTH_LONG)
                    .show();

            counter++;

        }
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("SERVICE", "Service stopped ");
    }
}

