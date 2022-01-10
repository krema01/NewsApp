package com.example.canteenchecker.mynews.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


public class HomeActivity extends AppCompatActivity {
    private String TAG = getClass().getName();
    //Constants constants = new Constants();


    private static boolean firstInit = true;

    private static final String FILTER_KEYWORD = "keyword";

    private MenuItem moreFilters;
    private MenuItem help;

    private NewsArticleAdapter newsArticleAdapter = new NewsArticleAdapter();

    private SwipeRefreshLayout srlSwipeRefreshLayout;
    EditText keywordSearch;
    Button btnSearch;
    LinearLayout logoLayout;

    public static Collection<NewsArticle> allArticles = new ArrayList<NewsArticle>();
    public static String lastKeyword;


    public static Intent createIntent(Context context, String keyword) {
        Log.e("REACHED HomeActivityView: ", "HURRAY");
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(FILTER_KEYWORD, keyword);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //SharedPreferences settings1 = getApplicationContext().getSharedPreferences(Constants.API, 0);
        //SharedPreferences.Editor editor = settings1.edit();
        //editor.putString("api", "pub_3299d32b8b154373c88df9cbebb156b295d3");
        //editor.apply();
        //Log.e("API IN HOME1", " " + settings1.getString("api", null));


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

        if(firstInit) {
            String countryCodeValue = Locale.getDefault().getCountry().toLowerCase();
            Log.e("COUNTRYCODE:", " = " + countryCodeValue);
            FilterSettings.setCountriesFilter("country=" + countryCodeValue);
            Collection<String> countriesFull = new ArrayList<>();
            Locale loc = new Locale("",countryCodeValue);
            countriesFull.add(loc.getDisplayCountry());
            FilterSettings.setCountriesFullName(countriesFull);
        }
        else{
            keywordSearch.setText(getIntent().getStringExtra(FILTER_KEYWORD));
        }

        logoLayout = findViewById(R.id.homeLogo);


        showNews();
        firstInit = false;
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

    private void showNews() {
        String keywordString = parseKeywords();
        Log.e("Keywords: ", " " + keywordString + "|" + lastKeyword);
        Log.e("ArticleCount: ", " " + allArticles.size());
        if(keywordString == lastKeyword) {
            if (allArticles.size() > 0) {
                logoLayout.setVisibility(LinearLayout.GONE);
                Log.e("shownews", "SETTING INVISIBLE!");
                newsArticleAdapter.displayNewsArticles(allArticles);
            }
            else{
                Log.e("shownews", "SETTING VISIBLE!");
                logoLayout.setVisibility(LinearLayout.VISIBLE);
            }
        }
        else updateNews();
    }

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
                        int i = 0;
                        for (; i < results.length(); i++){
                            addToList(results.getJSONObject(i));
                        }
                        if(i > 0){
                            Log.e("updatenews", "SETTING INVISIBLE!");
                            logoLayout.setVisibility(LinearLayout.GONE);
                            Log.e("AHSF", "DO SHIT! " + results.length());
                        }
                        else {
                            Log.e("updatenews", "SETTING INVISIBLE!");
                            logoLayout.setVisibility(LinearLayout.VISIBLE);
                        }
                    }
                    return allArticles;
                }catch(Exception e){
                    Log.e(TAG, "1" + e.getMessage());
                    Log.e("update exception", "SETTING VISIBLE!");
                    logoLayout.setVisibility(LinearLayout.VISIBLE);
                    return null;
                }
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
        if(!keywordSearch.getText().toString().matches("")){
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
            sb.append("page=");
            sb.append(FilterSettings.getPage());
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
        return sb.toString().replace("and", "AND")
                .replace("And", "AND")
                .replace("or", "OR")
                .replace("Or", "OR");
    }

    private void addToList(JSONObject jsonObject) throws JSONException {
        String fullDescription = "";
        if(jsonObject.has("full_description")) fullDescription = jsonObject.getString("full_description");
        NewsArticle article = new NewsArticle(jsonObject.getString("title"),
                                              jsonObject.getString("link"),
                jsonObject.getString("description"),
                jsonObject.getString("pubDate"),
                jsonObject.getString("image_url"),
                jsonObject.getString("source_id"),
                fullDescription
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

                itemTitleView.setText(newsArticle.getTitle());
                itemSourceView.setText(Constants.COPYRIGHT + newsArticle.getSourceID());
                itemPublishDateView.setText(newsArticle.getPublishDate());

                if(newsArticle.getImageUrl() != null && !newsArticle.getImageUrl().matches("null")
                        && !newsArticle.getImageUrl().matches("")) {
                    itemImageView.setImageBitmap(newsArticle.getImageBitmap());
                    itemImageView.setVisibility(View.VISIBLE);

                }else {
                    itemImageView.setVisibility(View.GONE);
                    //itemView.setOnClickListener(v -> v.getContext()
                    //        .startActivity(NewsArticleDetailsActivity
                    //                .createIntent(v.getContext(), newsArticle.getTitle(),
                    //                        newsArticle.getPublishDate(),
                    //                        newsArticle.getSourceID(), newsArticle.getDescription(),
                    //                        newsArticle.getArticleUrl())));
                }
                //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //newsArticle.getImageBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
                //byte[] byteArray = stream.toByteArray();
                itemView.setOnClickListener(v -> v.getContext()
                        .startActivity(NewsArticleDetailsActivity
                                .createIntent(v.getContext(), newsArticle.getTitle(),
                                        newsArticle.getImageUrl(), newsArticle.getPublishDate(),
                                        newsArticle.getSourceID(), newsArticle.getDescription(),
                                        newsArticle.getArticleUrl(), newsArticle.getFullDescription())));

                //itemView.setOnClickListener(new View.OnClickListener(){
//
                //    @Override
                //    public void onClick(View v) {
                //        Log.e("CLICK:", "NOT IMPLEMENTED YET");
                //        //detailsIntent.putExtra("message", message);
                //        //startActivity(intent);
//
                //        //Todo: Switch to Article View (create article view first)
                //    }
                //});
            }
        }
    }

    private void setImage(ImageView itemImageView, NewsArticle newsArticle) {
        String imageUrl = newsArticle.getImageUrl();
        Glide.with(HomeActivity.this).load(imageUrl).into(itemImageView);
    }
}


