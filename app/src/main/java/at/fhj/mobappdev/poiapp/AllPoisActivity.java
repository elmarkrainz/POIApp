package at.fhj.mobappdev.poiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.fhj.mobappdev.poiapp.db.PoiDataAccess;
import at.fhj.mobappdev.poiapp.db.PoiObject;

public class AllPoisActivity extends AppCompatActivity {


    private ArrayAdapter<String> adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pois);


        lv = (ListView) findViewById(R.id.listPois);

        loadListView();
    }

    private void loadListView() {

        List<String> pois = new ArrayList<String>();

        PoiDataAccess poidata = new PoiDataAccess(this);
        for (PoiObject p : poidata.getAllPois()){
            pois.add(p.toShortString());
        }

                adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pois);
        lv.setAdapter(adapter);
    }
}
