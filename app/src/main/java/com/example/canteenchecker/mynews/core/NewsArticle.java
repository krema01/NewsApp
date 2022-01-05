package com.example.canteenchecker.mynews.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.net.URL;
import java.util.Date;

public class NewsArticle {
    String title;
    String articleUrl;
    String description;
    String publishDate;
    String imageUrl;
    Bitmap imageBitmap;
    String sourceID;

    public NewsArticle(String title, String articleUrl, String description, String publishDate, String imageUrl, String sourceID){
        this.title = title;
        this.articleUrl = articleUrl;
        this.description = description;
        this.publishDate = publishDate;
        this.imageUrl = imageUrl;
        this.sourceID = sourceID;

        if(imageUrl != null){
            try {
                URL url = new URL(imageUrl);
                Log.e("IMAGE_URL=", url.toString());
                imageBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }catch(Exception e){Log.e("IMAGE:", "not available");}

        }
    }

    public String getTitle(){
        return title;
    }
    public String getArticleUrl(){
        return articleUrl;
    }
    public String getDescription(){
        return description;
    }
    public String getPublishDate(){
        return publishDate;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getSourceID() { return sourceID; }

    public Bitmap getImageBitmap() { return imageBitmap; }
}
