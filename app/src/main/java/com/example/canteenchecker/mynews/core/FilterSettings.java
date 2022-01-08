package com.example.canteenchecker.mynews.core;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

public class FilterSettings {
    static String countriesFilter = null;
    static Collection<String> countriesFullName = null;
    static String languagesFilter = null;
    static Collection<String> languagesFullName = null;
    static String categoriesFilter = null;
    static Collection<String> categoriesFullName = null;
    static String dateFromFilter = null;
    static String dateToFilter = null;
    static int page = 0;

    public static String getCategoriesFilter() {
        return categoriesFilter;
    }
    public static Collection<String> getCategoriesFullName() {
        Log.e("FilterSettings GET VALUES:", "Categories:" + categoriesFullName + ".");
        if(categoriesFullName != null)
            return categoriesFullName;
        else
            return new ArrayList<>();
    }

    public static String getCountriesFilter() {
        return countriesFilter;
    }

    public static Collection<String> getCountriesFullName() {
        if(countriesFullName != null)
            return countriesFullName;
        else
            return new ArrayList<>();
    }

    public static Collection<String> getLanguagesFullName() {
        if(languagesFullName != null)
            return languagesFullName;
        else
            return new ArrayList<>();
    }

    public static String getLanguagesFilter() {
        return languagesFilter;
    }

    public static String getDateFromFilter() { return dateFromFilter; }

    public static String getDateToFilter() { return dateToFilter; }

    public static int getPage() { return page; }

    public static void setCategoriesFilter(String categoriesFilter) {
        FilterSettings.categoriesFilter = categoriesFilter;
        Log.e("FilterSettings", "Categories:" + categoriesFilter + ".");
    }

    public static void setCategoriesFullName(Collection<String> categoriesFullName) {
        FilterSettings.countriesFullName = categoriesFullName;
        Log.e("FilterSettings SET TO:", "Categories:" + categoriesFullName + ".");
    }

    public static void setCountriesFilter(String countriesFilter) {
        FilterSettings.countriesFilter = countriesFilter;
        Log.e("FilterSettings", "Countries:" + countriesFilter + ".");
    }

    public static void setCountriesFullName(Collection<String> countriesFullName) {
        FilterSettings.countriesFullName = countriesFullName;
        Log.e("FilterSettings", "Countries:" + countriesFullName + ".");
    }

    public static void setLanguagesFilter(String languagesFilter) {
        FilterSettings.languagesFilter = languagesFilter;
        Log.e("FilterSettings", "Languages:" + languagesFilter + ".");
    }

    public static void setLanguagesFullName(Collection<String> languagesFullName) {
        FilterSettings.languagesFullName = languagesFullName;
        Log.e("FilterSettings", "Languages:" + languagesFullName + ".");
    }

    public static void setDateFromFilter(String dateFromFilter) {
        FilterSettings.dateFromFilter = dateFromFilter;
        Log.e("FilterSettings", "DateFrom:" + dateFromFilter + ".");
    }

    public static void setDateToFilter(String dateToFilter) {
        FilterSettings.dateToFilter = dateToFilter;
        Log.e("FilterSettings", "DateTo:" + dateToFilter + ".");
    }
    public static void setPage(int page) {
        FilterSettings.page = page;
        Log.e("FilterSettings", "Page:" + page + ".");
    }
}
