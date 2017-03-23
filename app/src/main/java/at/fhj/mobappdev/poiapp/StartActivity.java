package at.fhj.mobappdev.poiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Startactivity
 *
 * @author EKrainz
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void openAddPoi(View v) {

        //Intent to start another activity
        Intent i = new Intent(this, NewPoiActivity.class);
        startActivity(i);

    }

    public void openAllPois(View v) {
        Intent i = new Intent(this, AllPoisActivity.class);
        startActivity(i);
    }


    public void openLogger(View v) {
        Intent i = new Intent(this, GpsLoggerActivity.class);
        startActivity(i);
    }
}
