package com.example.canteenchecker.mynews.core;

import android.content.Context;
import android.util.Log;

import com.example.canteenchecker.mynews.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {
    private Context context;
    public static final String BASE_URL = "https://newsdata.io/api/1/news?apikey=";
    public static String API = null;
    public static final String AND = "&";

    public static final String COPYRIGHT = "\u00a9";

    public static Map<String, String> COUNTRIES;
    public static Map<String, String> LANGUAGES;
    public static Map<String, String> CATEGORIES;

    public Constants(Context context){
        this.context = context;
        Log.e("CONSTANTS: ", " " + context.getResources().getString(R.string.argentina));
        setCountries();
        setLanguages();
        setCategories();
    }

    private void setCountries() {
        COUNTRIES = new LinkedHashMap<String, String>()
        {
            {
                put(context.getResources().getString(R.string.argentina), "ar");
                put(context.getResources().getString(R.string.australia), "au");
                put(context.getResources().getString(R.string.austria), "at");
                put(context.getResources().getString(R.string.belgium), "be");
                put(context.getResources().getString(R.string.brazil), "br");
                put(context.getResources().getString(R.string.bulgaria), "bg");
                put(context.getResources().getString(R.string.canada), "ca");
                put(context.getResources().getString(R.string.china), "cn");
                put(context.getResources().getString(R.string.colombia), "co");
                put(context.getResources().getString(R.string.cuba), "cu");
                put(context.getResources().getString(R.string.czech_republik), "cz");
                put(context.getResources().getString(R.string.egypt), "eg");
                put(context.getResources().getString(R.string.france), "fr");
                put(context.getResources().getString(R.string.germany), "de");
                put(context.getResources().getString(R.string.greece), "gr");
                put(context.getResources().getString(R.string.hong_kong), "hk");
                put(context.getResources().getString(R.string.hungary), "hu");
                put(context.getResources().getString(R.string.indonesia), "in");
                put(context.getResources().getString(R.string.indonesia), "id");
                put(context.getResources().getString(R.string.ireland), "ie");
                put(context.getResources().getString(R.string.israel), "il");
                put(context.getResources().getString(R.string.italy), "it");
                put(context.getResources().getString(R.string.japan), "jp");
                put(context.getResources().getString(R.string.latvia), "lv");
                put(context.getResources().getString(R.string.lebanon), "lb");
                put(context.getResources().getString(R.string.lithuania), "lt");
                put(context.getResources().getString(R.string.malaysia), "my");
                put(context.getResources().getString(R.string.mexico), "mx");
                put(context.getResources().getString(R.string.morocco), "ma");
                put(context.getResources().getString(R.string.netherlands), "nl");
                put(context.getResources().getString(R.string.new_zealand), "nz");
                put(context.getResources().getString(R.string.nigeria), "ng");
                put(context.getResources().getString(R.string.north_korea), "kp");
                put(context.getResources().getString(R.string.norway), "no");
                put(context.getResources().getString(R.string.pakistan), "pk");
                put(context.getResources().getString(R.string.phillipines), "ph");
                put(context.getResources().getString(R.string.poland), "pl");
                put(context.getResources().getString(R.string.portugal), "pt");
                put(context.getResources().getString(R.string.romania), "ro");
                put(context.getResources().getString(R.string.russia), "ru");
                put(context.getResources().getString(R.string.saudi_arabia), "sa");
                put(context.getResources().getString(R.string.serbia), "rs");
                put(context.getResources().getString(R.string.singapore), "sg");
                put(context.getResources().getString(R.string.slovakia), "sk");
                put(context.getResources().getString(R.string.slovenia), "si");
                put(context.getResources().getString(R.string.south_africa), "za");
                put(context.getResources().getString(R.string.south_korea), "kr");
                put(context.getResources().getString(R.string.spain), "es");
                put(context.getResources().getString(R.string.sweden), "se");
                put(context.getResources().getString(R.string.switzerland), "ch");
                put(context.getResources().getString(R.string.taiwan), "tw");
                put(context.getResources().getString(R.string.thailand), "th");
                put(context.getResources().getString(R.string.turkey), "tr");
                put(context.getResources().getString(R.string.ukraine), "ua");
                put(context.getResources().getString(R.string.united_arab_emirates), "ae");
                put(context.getResources().getString(R.string.united_kingdom), "gb");
                put(context.getResources().getString(R.string.united_states_of_america), "us");
                put(context.getResources().getString(R.string.venezuela), "vz");
            }
        };
    }

    private void setLanguages() {
        LANGUAGES = new LinkedHashMap<String, String>(){
            {
                put(context.getResources().getString(R.string.arabic),"ar");
                put(context.getResources().getString(R.string.bosnian),"bs");
                put(context.getResources().getString(R.string.bulgarian),"bg");
                put(context.getResources().getString(R.string.chinese),"zh");
                put(context.getResources().getString(R.string.croatian),"hr");
                put(context.getResources().getString(R.string.czech),"cs");
                put(context.getResources().getString(R.string.dutch),"nl");
                put(context.getResources().getString(R.string.english),"en");
                put(context.getResources().getString(R.string.french),"fr");
                put(context.getResources().getString(R.string.german),"de");
                put(context.getResources().getString(R.string.greek),"el");
                put(context.getResources().getString(R.string.hebrew),"he");
                put(context.getResources().getString(R.string.hindi),"hi");
                put(context.getResources().getString(R.string.hungarian),"hu");
                put(context.getResources().getString(R.string.indonesian),"in");
                put(context.getResources().getString(R.string.italian),"it");
                put(context.getResources().getString(R.string.japanese),"jp");
                put(context.getResources().getString(R.string.korean),"ko");
                put(context.getResources().getString(R.string.latvian),"lv");
                put(context.getResources().getString(R.string.lithuanian),"lt");
                put(context.getResources().getString(R.string.malay),"ms");
                put(context.getResources().getString(R.string.norwegian),"pl");
                put(context.getResources().getString(R.string.polish),"pt");
                put(context.getResources().getString(R.string.portuguese),"ro");
                put(context.getResources().getString(R.string.romanian),"ru");
                put(context.getResources().getString(R.string.serbian),"sr");
                put(context.getResources().getString(R.string.slovak),"sk");
                put(context.getResources().getString(R.string.slovenian),"sl");
                put(context.getResources().getString(R.string.spanish),"es");
                put(context.getResources().getString(R.string.swedish),"sv");
                put(context.getResources().getString(R.string.thai),"th");
                put(context.getResources().getString(R.string.turkish),"tr");
                put(context.getResources().getString(R.string.ukranian),"uk");
            }
        };
    }

    private void setCategories() {
        CATEGORIES = new LinkedHashMap<String, String>();
        CATEGORIES.put(context.getResources().getString(R.string.business), "business");
        CATEGORIES.put(context.getResources().getString(R.string.entertainment), "entertainment");
        CATEGORIES.put(context.getResources().getString(R.string.environment), "environment");
        CATEGORIES.put(context.getResources().getString(R.string.food), "food");
        CATEGORIES.put(context.getResources().getString(R.string.health), "health");
        CATEGORIES.put(context.getResources().getString(R.string.politics), "politics");
        CATEGORIES.put(context.getResources().getString(R.string.science), "science");
        CATEGORIES.put(context.getResources().getString(R.string.sports), "sports");
        CATEGORIES.put(context.getResources().getString(R.string.technology), "technology");
        CATEGORIES.put(context.getResources().getString(R.string.top), "top");
        CATEGORIES.put(context.getResources().getString(R.string.world), "world");
    }
}



