package com.example.canteenchecker.mynews.core;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String BASE_URL = "https://newsdata.io/api/1/news?apikey=";
    public static final String API = "pub_3299d32b8b154373c88df9cbebb156b295d3";
    public static final String AND = "&";

    public static final String COPYRIGHT = "\u00a9";

    public static final Map<String, String> COUNTRIES = new HashMap<String, String>()
    {
        {
            put("Argentina", "ar");
            put("Australia", "au");
            put("Austria", "at");
            put("Belgium", "be");
            put("Brazil", "br");
            put("Bulgaria", "bg");
            put("Canada", "ca");
            put("China", "cn");
            put("Colombia", "co");
            put("Cuba", "cu");
            put("Czech Republic", "cz");
            put("Egypt", "eg");
            put("France", "fr");
            put("Germany", "de");
            put("Greece", "gr");
            put("Hong Kong", "hk");
            put("Hungary", "hu");
            put("India", "in");
            put("Indonesia", "id");
            put("Ireland", "ie");
            put("Israel", "il");
            put("Italy", "it");
            put("Japan", "jp");
            put("Latvia", "lv");
            put("Lebanon", "lv");
            put("Lithuania", "lt");
            put("Malaysia", "my");
            put("Mexico", "mx");
            put("Morocco", "ma");
            put("Netherlands", "nl");
            put("New Zealand", "nz");
            put("Nigeria", "ng");
            put("North Korea", "kp");
            put("Norway", "no");
            put("Pakistan", "pk");
            put("Philippines", "ph");
            put("Poland", "pl");
            put("Portugal", "pt");
            put("Romania", "ro");
            put("Russia", "ru");
            put("Saudi Arabia", "sa");
            put("Serbia", "rs");
            put("Singapore", "sg");
            put("Slovakia", "sk");
            put("Slovenia", "si");
            put("South Africa", "za");
            put("South Korea", "kr");
            put("Spain", "es");
            put("Sweden", "se");
            put("Switzerland", "ch");
            put("Taiwan", "tw");
            put("Thailand", "th");
            put("Turkey", "tr");
            put("Ukraine", "ua");
            put("United Arab Emirates", "ae");
            put("United Kingdom", "gb");
            put("United State of America", "us");
            put("Venezuela", "ve");
        }
    };


    //public static final String[] COUNTRIES_ABBREVIATIONS = {
    //        "ar", "au", "at", "be", "br", "bg", "ca", "cn", "co",
    //        "cu", "cz", "eg", "fr", "de", "gr", "hk", "hu", "in",
    //        "id", "ie", "il", "it", "jp", "lv", "lb", "lt", "my",
    //        "mx", "ma", "nl", "nz", "ng", "kp", "no", "pk", "ph",
    //        "pl", "pt", "ro", "ru", "sa", "rs", "sg", "sk", "si",
    //        "za", "kr", "es", "se", "ch", "tw", "th", "tr", "ua",
    //        "ae", "gb", "us", "ve"
    //};
    //public static final String[] COUNTRIES_AVAILABLE = {
    //        "Argentina", "Australia", "Austria", "Belgium",
    //        "Brazil", "Bulgaria", "Canada", "China", "Colombia",
    //        "Cuba", "Czech Republic", "Egypt", "France", "Germany",
    //        "Greece", "Hong Kong", "Hungary", "India", "Indonesia",
    //        "Ireland", "Israel", "Italy", "Japan", "Latvia", "Lebanon",
    //        "Lithuania", "Malaysia", "Mexico", "Morocco", "Netherlands",
    //        "New Zealand", "Nigeria", "North Korea", "Norway", "Pakistan",
    //        "Philippines", "Poland", "Portugal", "Romania", "Russia",
    //        "Saudi Arabia", "Serbia", "Singapore", "Slovakia", "Slovenia",
    //        "South Africa", "South Korea", "Spain", "Sweden", "Switzerland",
    //        "Taiwan", "Thailand", "Turkey", "Ukraine", "United Arab Emirates",
    //        "United Kingdom", "United State of America", "Venezuela"
    //};


    public static final Map<String, String> LANGUAGES = new HashMap<String, String>(){
        {
            put("Arabic", "ar");
            put("Bosnian", "bs");
            put("Bulgarian", "bg");
            put("Chinese", "zh");
            put("Croatian", "hr");
            put("Czech", "cs");
            put("Dutch", "nl");
            put("English", "en");
            put("French", "fr");
            put("German", "de");
            put("Greek", "el");
            put("Hebrew", "he");
            put("Hindi", "hi");
            put("Hungarian", "hu");
            put("Indonesian", "in");
            put("Italian", "it");
            put("Japanese", "jp");
            put("Korean", "ko");
            put("Latvian", "lv");
            put("Lithuanian", "lt");
            put("Malay", "ms");
            put("Norwegian", "pl");
            put("Polish", "pt");
            put("Portuguese", "ro");
            put("Romanian", "ru");
            put("Serbian", "sr");
            put("Slovak", "sk");
            put("Slovenian", "sl");
            put("Spanish", "es");
            put("Swedish", "sv");
            put("Thai", "th");
            put("Turkish", "tr");
            put("Ukranian", "uk");
        }
    };

   // public static final String[] LANGUAGES_AVAILABLE = {
   //         "Arabic", "Bosnian", "Bulgarian", "Chinese", "Croatian", "Czech",
   //         "Dutch", "English", "French", "German", "Greek", "Hebrew", "Hindi",
   //         "Hungarian", "Indonesian", "Italian", "Japanese", "Korean",
   //         "Latvian", "Lithuanian", "Malay", "Norwegian", "Polish", "Portuguese",
   //         "Romanian", "Serbian", "Slovak", "Slovenian", "Spanish", "Swedish",
   //         "Thai", "Turkish", "Ukranian"
   // };
//
   // public static final String[] LANGUAGES_ABBREVIATIONS = {
   //         "ar", "bs", "bg", "zh", "hr", "cs", "nl", "en", "fr", "de", "el",
   //         "he", "hi", "hu", "in", "it", "jp", "ko", "lv", "lt", "ms", "no",
   //         "pl", "pt", "ro", "ru", "sr", "sk", "sl", "es", "sv", "th", "tr", "uk"
   // };

    public static final String[] CATEGORIES = {
            "Business", "Entertainment", "Environment", "Food", "Health",
            "Poltics", "Science", "Sports", "Technology", "Top", "World"
    };
}



