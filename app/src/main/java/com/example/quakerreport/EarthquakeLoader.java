package com.example.quakerreport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthQuick>> {

    private String mUrl;

    public EarthquakeLoader(Context context,String url) {
        super(context);
        mUrl=url;
    }

   @Override
   protected void onStartLoading(){
        forceLoad();
   }

    @Override
    public List<EarthQuick> loadInBackground() {
       if(mUrl==null){
           return null;
       }
       List<EarthQuick> earthQuicks = QueryUtils.fetchEarthquakeData(mUrl);
       return earthQuicks;

    }
}
