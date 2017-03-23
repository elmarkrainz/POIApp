package at.fhj.mobappdev.poiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GpsLoggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_logger);
    }


    public void startLogging (View v){

        Intent i = new Intent(this, GPSLoggingService.class);
        startService(i);

    }
}
