package com.example.quakerreport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<EarthQuick>> {

    private EarthQuickAdapter mAdapter;

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    private static final int EARTHQUAKE_LOADER_ID =1;

    private TextView isEmptystateTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = findViewById(R.id.list);
        mAdapter = new EarthQuickAdapter(MainActivity.this, new ArrayList<EarthQuick>());



        isEmptystateTextView = findViewById(R.id.empty_view);

        listView.setEmptyView(isEmptystateTextView);
            listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EarthQuick earth = mAdapter.getItem(position);

                OpenUrl(earth.getUrl());

            }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
        }
        else{
            View LoadingIndicator = findViewById(R.id.loading_indicator);
            LoadingIndicator.setVisibility(View.GONE);
            isEmptystateTextView.setText(R.string.no_internet);
        }


    }


  @Override
  public Loader<List<EarthQuick>> onCreateLoader(int i , Bundle bundle){
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
  }
  @Override
  public void onLoadFinished(Loader<List<EarthQuick>> loader, List<EarthQuick> earthQuicks) {
        View LoadingIndicator = findViewById(R.id.loading_indicator);
        LoadingIndicator.setVisibility(View.GONE);

        isEmptystateTextView.setText(R.string.no_earthquakes);

        mAdapter.clear();

      if (earthQuicks != null && !earthQuicks.isEmpty()) {
          mAdapter.addAll(earthQuicks);
      }

  }
  @Override
  public void onLoaderReset(Loader<List<EarthQuick>> loader){
        mAdapter.clear();
  }


    private void OpenUrl(String url) {
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "No application can handle this request." + " Please install a webbrowser", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}