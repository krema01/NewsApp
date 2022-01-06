package com.example.canteenchecker.mynews.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;
import com.example.canteenchecker.mynews.core.NewsArticle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NewsArticleDetailsActivity extends AppCompatActivity {

    static boolean hasImage = false;
    private static final String ARTICLE_TITLE = "title";
    private static final String ARTICLE_IMAGE = "image";
    private static final String ARTICLE_PUBLISH_DATE = "publishDate";
    private static final String ARTICLE_SOURCE = "source";
    private static final String ARTICLE_DESCRIPTION = "description";
    private static final String ARTICLE_LINK = "link";
    private static final String ARTICLE_FULL_DESCRIPTION = "fullDescription";


    private View        detailsProgressView;
    private View        detailsContentView;
    private ImageView   detailsImageView;
    private TextView    detailsTitleView;
    private TextView    detailsPublishDateView;
    private TextView    detailsSourceView;
    private TextView    detailsDescriptionView;
    private TextView    detailsFullDescriptionView;
    private TextView    detailsLinkView;





    public static Intent createIntent(Context context, String title, String image,
                                                       String publishDate, String source,
                                                       String description, String link, String fullDescription) {
        Log.e("REACHED NEWSARTICLEDETAILSVIEW: ", "HURRAY");
        Intent intent = new Intent(context, NewsArticleDetailsActivity.class);
        intent.putExtra(ARTICLE_TITLE, title);
        intent.putExtra(ARTICLE_IMAGE, image);
        intent.putExtra(ARTICLE_PUBLISH_DATE, publishDate);
        intent.putExtra(ARTICLE_SOURCE, source);
        intent.putExtra(ARTICLE_DESCRIPTION, description);
        intent.putExtra(ARTICLE_LINK, link);
        intent.putExtra(ARTICLE_FULL_DESCRIPTION, fullDescription);
        hasImage = true;
        return intent;
    }
    //public static Intent createIntent(Context context, String title,
    //                                  String publishDate, String source,
    //                                  String description, String link) {
    //    Log.e("REACHED NEWSARTICLEDETAILSVIEW: ", "HURRAY");
    //    Intent intent = new Intent(context, NewsArticleDetailsActivity.class);
    //    intent.putExtra(ARTICLE_TITLE, title);
    //    intent.putExtra(ARTICLE_PUBLISH_DATE, publishDate);
    //    intent.putExtra(ARTICLE_SOURCE, source);
    //    intent.putExtra(ARTICLE_DESCRIPTION, description);
    //    intent.putExtra(ARTICLE_LINK, link);
    //    return intent;
    //}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article_details);

        String title = getIntent().getStringExtra(ARTICLE_TITLE);
        String image = getIntent().getStringExtra(ARTICLE_IMAGE);
        String publishDate = getIntent().getStringExtra(ARTICLE_PUBLISH_DATE);
        String source = getIntent().getStringExtra(ARTICLE_SOURCE);
        String description = getIntent().getStringExtra(ARTICLE_DESCRIPTION);
        String fullDescription = getIntent().getStringExtra(ARTICLE_FULL_DESCRIPTION);
        String link = getIntent().getStringExtra(ARTICLE_LINK);




        detailsProgressView = findViewById(R.id.detailsProgressView);
        detailsContentView = findViewById(R.id.detailsContentView);
        detailsTitleView = findViewById(R.id.detailsTitleView);
        detailsImageView = findViewById(R.id.detailsImage);
        detailsPublishDateView = findViewById(R.id.detailsPublishDateView);
        detailsSourceView = findViewById(R.id.detailsSourceView);
        detailsDescriptionView = findViewById(R.id.detailsDescriptionView);
        detailsFullDescriptionView = findViewById(R.id.detailsFullDescriptionView);
        detailsLinkView = findViewById(R.id.detailsLinkView);


        detailsTitleView.setText(title);
        detailsPublishDateView.setText(publishDate);
        detailsSourceView.setText(Constants.COPYRIGHT + source);
        detailsDescriptionView.setText(description);
        detailsFullDescriptionView.setText(fullDescription);
        detailsLinkView.setText(link);

        Log.e("DESCRIPTION: ", description);
        Log.e("FULL DESCRIPTION: ", fullDescription);
        if(fullDescription == null || fullDescription.matches("null")) detailsFullDescriptionView.setVisibility(View.GONE);
        if(image != null && !image.matches("null") && !image.matches("")){
            new DownloadImageTask(detailsImageView).execute(image);
        }

        //if(hasImage) {
        //    byte[] imageBytes = getIntent().getByteArrayExtra(ARTICLE_IMAGE);
        //    Bitmap image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        //    detailsImageView.setImageBitmap(image);
        //}

    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap imageBitmap = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                imageBitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //  Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return imageBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
















