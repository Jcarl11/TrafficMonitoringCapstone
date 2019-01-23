package com.example.trafficmonitoring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.levitnudi.legacytableview.LegacyTableView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LOSReport extends AppCompatActivity {

    LegacyTableView legacyTableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losreport);
        ArrayList<String> dataList = getIntent().getStringArrayListExtra("data");
        LegacyTableView.insertLegacyTitle("Timestamp", "Facility", "Facility Type", "LOS");
        for(String data : dataList)
        {
            try {
                JSONObject json = new JSONObject(data);
                String timeStamp = json.getString("timeStamp");
                String facility = json.getString("facility");
                String facilityType = json.getString("facilityType");
                String LOS = json.getString("LOS");
                LegacyTableView.insertLegacyContent(timeStamp, facility, facilityType, LOS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        legacyTableView = (LegacyTableView)findViewById(R.id.legacy_table_view);
        legacyTableView.setTitle(LegacyTableView.readLegacyTitle());
        legacyTableView.setContent(LegacyTableView.readLegacyContent());
        legacyTableView.setTheme(LegacyTableView.GOLDALINE);
        legacyTableView.setZoomEnabled(true);
        legacyTableView.setShowZoomControls(true);
        legacyTableView.build();
    }
}
