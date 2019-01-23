package com.example.trafficmonitoring;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Report2TaskExecute extends AsyncTask<Void, Void, ArrayList<String>> {
    private boolean finished = false;
    ArrayList<String> reportList = new ArrayList<>();
    public Report2TaskExecute(ProgressBar progressBar, Context context) {
        this.progressBar = progressBar;
        this.context = context;
    }

    ProgressBar progressBar;
    Context context;
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Data");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                if(e == null && objects != null)
                {
                    for(ParseObject data : objects)
                    {
                        Report2Entity entity = new Report2Entity();
                        entity.setTimeStamp(data.getDate("TIMESTAMP"));
                        entity.setFacility(data.getString("Facility"));
                        entity.setFacilityType(data.getString("FacilityType"));
                        entity.setLOS(data.getString("LOS"));
                        reportList.add(entity.toJSON());
                    }
                    finished = true;
                }
                else
                    finished = true;
            }
        });
        while(finished == false)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return reportList;
    }

    @Override
    protected void onPreExecute() {
       progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        progressBar.setVisibility(View.INVISIBLE);
        if(strings.size() > 0) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("data", strings);
            context.startActivity(new Intent(context, LOSReport.class).putExtras(bundle));
        }
        else
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
}
