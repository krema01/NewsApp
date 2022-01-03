package com.example.canteenchecker.mynews.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;
import com.example.canteenchecker.mynews.core.FilterSettings;
import com.example.canteenchecker.mynews.core.NewsArticle;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class HomeActivity extends AppCompatActivity {

    private String TAG = getClass().getName();

    public Collection<NewsArticle> allArticles = new ArrayList<NewsArticle>();
    private SwipeRefreshLayout srlSwipeRefreshLayout;

    boolean[] selectedCountries =  new boolean[Constants.COUNTRIES_AVAILABLE.length];
    ArrayList<Integer> countriesList = new ArrayList<>();
    private MenuItem moreFilters;
    private MenuItem help;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate()");

        srlSwipeRefreshLayout = findViewById(R.id.srlSwipeRefreshLayout);
        srlSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Todo: load next page from API
            }
        });

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateNews();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_home_screen, menu);
        moreFilters = menu.findItem(R.id.mniMoreFilters);
        help = menu.findItem(R.id.mniHelp);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.mniMoreFilters).setVisible(true);
        menu.findItem(R.id.mniHelp).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.mniMoreFilters){
           //Todo: Set Activity to FiltersActivity
            Intent intent = new Intent(HomeActivity.this, FilterActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.mniHelp){
            //Todo: Set Activity to Help Activity
            Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        else{
            Toast.makeText(this, "Menu item not available", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("StaticFieldLeak")
    private void updateNews() {
        new AsyncTask<String, Void, Collection<NewsArticle>>(){

            @Override
            protected Collection<NewsArticle> doInBackground(String... strings) {

                try {
                    URL url = new URL(buildUrlWithFilters());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    Log.e(TAG, "success" + responseCode);

                    if(responseCode != 200){
                        throw new RuntimeException("HttpResponseCode: " + responseCode);
                    }
                    else{
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line+"\n");
                        }
                        br.close();
                        Log.e(TAG, "JSON: " + sb.toString());

                        String jsonString = sb.toString();
                        JSONObject obj = new JSONObject(jsonString);
                        JSONArray results = obj.getJSONArray("results");
                        Log.e(TAG, "RESULTS: " + results);
                        Gson gson = new Gson();
                        for (int i = 0; i < results.length(); i++){
                            Log.e(TAG, "RESULT: " + results.getJSONObject(i).toString());
                            addToList(results.getJSONObject(i));
                            Log.e(TAG, "allArticleCount: " + allArticles.size());
                        }
                    }
                    return null;
                }catch(Exception e){Log.e(TAG, "1" + e.getMessage()); return null;}
            }
        }.execute();
    }

    private String buildUrlWithFilters() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.BASE_URL);
        sb.append(Constants.API);
        if(FilterSettings.getCountriesFilter() != null) {
            sb.append(Constants.AND);
            sb.append(FilterSettings.getCountriesFilter());
        }
        if(FilterSettings.getCategoriesFilter() != null) {
            sb.append(Constants.AND);
            sb.append(FilterSettings.getCategoriesFilter());
        }
        if(FilterSettings.getLanguagesFilter() != null) {
            sb.append(Constants.AND);
            sb.append(FilterSettings.getLanguagesFilter());
        }
        //Todo: add q (filter for keyword)
        //Todo: add page (increase page count after using it once)

        return sb.toString();
    }

    private void addToList(JSONObject jsonObject) throws JSONException {
        //Log.e(TAG, "Title: " + jsonObject.getString("title"));
        //Log.e(TAG, "Link: " + jsonObject.getString("link"));
        //Log.e(TAG, "PublishDate: " + jsonObject.getString("pubDate"));
        //Log.e(TAG, "ImageURL: " + jsonObject.getString("image_url"));
        //Log.e(TAG, "Description: " + jsonObject.getString("description"));
        NewsArticle article = new NewsArticle(jsonObject.getString("title"),
                                              jsonObject.getString("link"),
                jsonObject.getString("description"),
                jsonObject.getString("pubDate"),
                jsonObject.getString("image_url")
        );
        allArticles.add(article);
    }
}


