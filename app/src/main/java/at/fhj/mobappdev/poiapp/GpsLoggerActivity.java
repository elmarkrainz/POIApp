package at.fhj.mobappdev.poiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * example of a Logging Service -
 * UI to start/stop the service
 *
 * @author EKrainz
 */
public class GpsLoggerActivity extends AppCompatActivity {

    Button btnStartStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_logger);
        btnStartStop = (Button) findViewById(R.id.btnStartStop);

    }


    public void startStopLogging(View v) {

        if (btnStartStop.getText().toString().equalsIgnoreCase("Start")) {

            btnStartStop.setText("Stop");
            startService();
        } else {
            btnStartStop.setText("Start");
            stopService();
        }

    }


    private void startService() {
        Intent i = new Intent(this, GPSLoggingService.class);
        startService(i);
    }


    private void stopService() {
        Intent i = new Intent(this, GPSLoggingService.class);
        stopService(i);
    }
}
