package com.example.canteenchecker.mynews.core;

import android.util.Log;

public class FilterSettings {
    static String countriesFilter = null;
    static String languagesFilter = null;
    static String categoriesFilter = null;
    static String dateFromFilter = null;
    static String dateToFilter = null;
    static int page = 0;

    public static String getCategoriesFilter() {
        return categoriesFilter;
    }

    public static String getCountriesFilter() {
        return countriesFilter;
    }

    public static String getLanguagesFilter() {
        return languagesFilter;
    }

    public static String getDateFromFilter() { return dateFromFilter; }

    public static String getDateToFilter() { return dateToFilter; }

    public static int getPage() { return page; }

    public static void setCategoriesFilter(String categoriesFilter) {
        FilterSettings.categoriesFilter = categoriesFilter;
        Log.e("FilterSettings", "Categories: " + categoriesFilter + ".");
    }

    public static void setCountriesFilter(String countriesFilter) {
        FilterSettings.countriesFilter = countriesFilter;
        Log.e("FilterSettings", "Countries: " + countriesFilter + ".");
    }

    public static void setLanguagesFilter(String languagesFilter) {
        FilterSettings.languagesFilter = languagesFilter;
        Log.e("FilterSettings", "Languages: " + languagesFilter + ".");
    }

    public static void setDateFromFilter(String dateFromFilter) {
        FilterSettings.dateFromFilter = dateFromFilter;
        Log.e("FilterSettings", "DateFrom: " + dateFromFilter + ".");
    }

    public static void setDateToFilter(String dateToFilter) {
        FilterSettings.dateToFilter = dateToFilter;
        Log.e("FilterSettings", "DateTo: " + dateToFilter + ".");
    }
    public static void setPage(int page) {
        FilterSettings.page = page;
        Log.e("FilterSettings", "Page: " + page + ".");
    }
}
