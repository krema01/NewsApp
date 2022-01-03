package com.example.canteenchecker.mynews.core;

import java.util.Date;

public class NewsArticle {
    String title;
    String articleUrl;
    String description;
    String publishDate;
    String imageUrl;

    public NewsArticle(String title, String articleUrl, String description, String publishDate, String imageUrl){
        this.title = title;
        this.articleUrl = articleUrl;
        this.description = description;
        this.publishDate = publishDate;
        this.imageUrl = imageUrl;
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
}
