package com.example.canteenchecker.mynews.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;
import com.example.canteenchecker.mynews.core.FilterSettings;
import com.example.canteenchecker.mynews.core.NewsArticle;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private String TAG = getClass().getName();

    private MenuItem moreFilters;
    private MenuItem help;

    private NewsArticleAdapter newsArticleAdapter = new NewsArticleAdapter();


    private SwipeRefreshLayout srlSwipeRefreshLayout;
    EditText keywordSearch;
    Button btnSearch;

    public Collection<NewsArticle> allArticles = new ArrayList<NewsArticle>();
    private String lastKeyword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rcvNews = findViewById(R.id.rcvNews);
        rcvNews.setLayoutManager(new LinearLayoutManager(this));
        rcvNews.setAdapter(newsArticleAdapter);

        srlSwipeRefreshLayout = findViewById(R.id.srlSwipeRefreshLayout);
        srlSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Todo: load next page from API
                updateNews();
            }
        });

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateNews();
            }
        });

        keywordSearch = findViewById(R.id.edtKeywordSearch);
    }

    /*========== Options Menu ==========*/
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


    /*========== Helper Functions ==========*/
    @SuppressLint("StaticFieldLeak")
    private void updateNews() {
        srlSwipeRefreshLayout.setRefreshing(true);
        new AsyncTask<String, Void, Collection<NewsArticle>>(){

            @Override
            protected Collection<NewsArticle> doInBackground(String... strings) {

                try {
                    URL url = new URL(buildUrlWithFilters());
                    Log.e("URL ==> ", url.toString());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    Log.e(TAG, "success" + responseCode);
                    if(responseCode != 200){
                        throw new RuntimeException("HttpResponseCode: " + responseCode);
                    }
                    else{
                        allArticles.clear();
                        FilterSettings.setPage(FilterSettings.getPage()+1);

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line+"\n");
                        }
                        br.close();

                        String jsonString = sb.toString();
                        JSONObject obj = new JSONObject(jsonString);
                        JSONArray results = obj.getJSONArray("results");

                        Gson gson = new Gson();
                        for (int i = 0; i < results.length(); i++){
                            addToList(results.getJSONObject(i));
                        }
                    }
                    return allArticles;
                }catch(Exception e){Log.e(TAG, "1" + e.getMessage()); return null;}
            }

            @Override
            protected void onPostExecute(Collection<NewsArticle> newsArticles) {
                srlSwipeRefreshLayout.setRefreshing(false);
                newsArticleAdapter.displayNewsArticles(newsArticles);
            }
        }.execute();
    }

    private String buildUrlWithFilters() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.BASE_URL);
        sb.append(Constants.API);
        if(FilterSettings.getCountriesFilter() != "" && FilterSettings.getCountriesFilter() != null) {
            sb.append(Constants.AND);
            sb.append(FilterSettings.getCountriesFilter());
        }
        if(FilterSettings.getCategoriesFilter() != "" && FilterSettings.getCategoriesFilter() != null) {
            sb.append(Constants.AND);
            sb.append(FilterSettings.getCategoriesFilter());
        }
        if(FilterSettings.getLanguagesFilter() != "" && FilterSettings.getLanguagesFilter() != null) {
            sb.append(Constants.AND);
            sb.append(FilterSettings.getLanguagesFilter());
        }
        if(FilterSettings.getDateFromFilter() != "" && FilterSettings.getDateFromFilter() != null){
            sb.append(Constants.AND);
            sb.append(FilterSettings.getDateFromFilter());
        }
        if(FilterSettings.getDateToFilter() != "" && FilterSettings.getDateToFilter() != null){
            sb.append(Constants.AND);
            sb.append(FilterSettings.getDateToFilter());
        }
        if(keywordSearch.getText().toString() != null && !keywordSearch.getText().toString().matches("")){
            String keywordString = parseKeywords();
            if(keywordString != lastKeyword){
                FilterSettings.setPage(0);
                lastKeyword = keywordString;
            }
            sb.append(Constants.AND + "q=");
            sb.append(keywordString);
        }
        if(FilterSettings.getPage() > 0){
            sb.append(Constants.AND);
            sb.append("page=" + FilterSettings.getPage());
        }

        return sb.toString();
    }

    private String parseKeywords() {
        StringBuilder sb = new StringBuilder();
        String keyword = keywordSearch.getText().toString();
        for(int i = 0; i < keyword.length(); ++i) {
            if (keyword.charAt(i) != ' ') sb.append(keyword.charAt(i));
            else sb.append("%20");
        }
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
                jsonObject.getString("image_url"),
                jsonObject.getString("source_id")
        );
        allArticles.add(article);
    }

    static private class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder>{

        private List<NewsArticle> newsArticleList = new ArrayList<>();

        @NonNull
        @Override
        public NewsArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_article, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsArticleAdapter.ViewHolder holder, int position) {
            holder.updateView(newsArticleList.get(position));
        }

        @Override
        public int getItemCount() {
            return newsArticleList.size();
        }

        void displayNewsArticles(Collection<NewsArticle> newsArticles){
            newsArticleList.clear();
            if(newsArticles != null){
                newsArticleList.addAll(newsArticles);
            }
            notifyDataSetChanged();
        }

        static class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView itemTitleView = itemView.findViewById(R.id.itemTitleView);
            private final TextView itemSourceView = itemView.findViewById(R.id.itemSourceView);
            private final TextView itemPublishDateView = itemView.findViewById(R.id.itemPublishDateView);
            //private final TextView itemDescriptionView = itemView.findViewById(R.id.itemDescriptionView);
            private final ImageView itemImageView = itemView.findViewById(R.id.itemImageView);

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            void updateView(NewsArticle newsArticle){
                Log.e("UPDATEVIEW:", newsArticle.getTitle());
                Log.e("IMAGEURL:", newsArticle.getImageUrl());
                itemTitleView.setText(newsArticle.getTitle());
                itemSourceView.setText(newsArticle.getSourceID());
                itemPublishDateView.setText(newsArticle.getPublishDate());

                if(newsArticle.getImageUrl() != null && !newsArticle.getImageUrl().matches("null")
                        && !newsArticle.getImageUrl().matches("")) {
                    Log.e("WASNT NULL", newsArticle.getTitle() + " " + newsArticle.getImageUrl());
                    itemImageView.setImageBitmap(newsArticle.getImageBitmap());
                    itemImageView.setVisibility(View.VISIBLE);
                }else {
                    itemImageView.setVisibility(View.GONE);
                }

                //itemDescriptionView.setText(newsArticle.getDescription());


                //setImage(itemImageView, newsArticle);


                itemView.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Log.e("CLICK:", "NOT IMPLEMENTED YET");
                        //Todo: Switch to Article View (create article view first)
                    }
                });
            }
        }
    }

    private void setImage(ImageView itemImageView, NewsArticle newsArticle) {
        String imageUrl = newsArticle.getImageUrl();
        Glide.with(HomeActivity.this).load(imageUrl).into(itemImageView);
    }
}


