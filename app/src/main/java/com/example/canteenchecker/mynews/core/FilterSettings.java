package com.example.canteenchecker.mynews.core;

import android.util.Log;

public class FilterSettings {
    static String countriesFilter;
    static String languagesFilter;
    static String categoriesFilter;
    static int page;

    public static String getCategoriesFilter() {
        return categoriesFilter;
    }

    public static String getCountriesFilter() {
        return countriesFilter;
    }

    public static String getLanguagesFilter() {
        return languagesFilter;
    }

    public static int getPage() {
        return page;
    }

    public static void setCategoriesFilter(String categoriesFilter) {
        FilterSettings.categoriesFilter = categoriesFilter;
        Log.e("FilterSettings", "Categories: " + categoriesFilter);
    }

    public static void setCountriesFilter(String countriesFilter) {
        FilterSettings.countriesFilter = countriesFilter;
        Log.e("FilterSettings", "Countries: " + countriesFilter);
    }

    public static void setLanguagesFilter(String languagesFilter) {
        FilterSettings.languagesFilter = languagesFilter;
        Log.e("FilterSettings", "Languages: " + languagesFilter);
    }

    public static void setPage(int page) {
        FilterSettings.page = page;
        Log.e("FilterSettings", "Page: " + page);
    }
}
