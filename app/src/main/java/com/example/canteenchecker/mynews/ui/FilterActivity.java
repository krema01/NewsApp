package com.example.canteenchecker.mynews.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;
import com.example.canteenchecker.mynews.core.FilterSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class FilterActivity extends AppCompatActivity {

    private MenuItem moreFilters;
    private MenuItem help;

    private Button saveFiltersButton;

    // initialize variables
    TextView countriesTextView;
    boolean[] selectedCountries;
    ArrayList<Integer> countriesList = new ArrayList<>();

    TextView languagesTextView;
    boolean[] selectedLanguages;
    ArrayList<Integer> languagesList = new ArrayList<>();

    TextView categoriesTextView;
    boolean[] selectedCategories;
    ArrayList<Integer> categoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        saveFiltersButton = findViewById(R.id.save_filters_button);
        saveFiltersButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateFilters();
            }
        });

        // assign variables
        countriesTextView = findViewById(R.id.countries_text_view);
        languagesTextView = findViewById(R.id.languages_text_view);
        categoriesTextView = findViewById(R.id.categories_text_view);

        // initialize selected language array
        selectedCountries = new boolean[Constants.COUNTRIES_AVAILABLE.length];
        selectedLanguages = new boolean[Constants.LANGUAGES_AVAILABLE.length];
        selectedCategories = new boolean[Constants.CATEGORIES_AVAILABLE.length];

        countriesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FilterActivity.this);

                // set title
                builder.setTitle("Select Countries");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(Constants.COUNTRIES_AVAILABLE, selectedCountries, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            countriesList.add(i);
                            // Sort array list
                            Collections.sort(countriesList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            countriesList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        stringBuilder.append("country=");
                        for (int j = 0; j < countriesList.size(); j++) {
                            // concat array value
                            stringBuilder.append(Constants.COUNTRIES_ABBREVIATIONS[countriesList.get(j)]);
                            // check condition
                            if (j != countriesList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(",");
                            }
                        }
                        // set text on textView
                        countriesTextView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedCountries.length; j++) {
                            // remove all selection
                            selectedCountries[j] = false;
                            // clear language list
                            countriesList.clear();
                            // clear text view value
                            countriesTextView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
        if(FilterSettings.getCountriesFilter() != null) countriesTextView.setText(FilterSettings.getCountriesFilter());

        languagesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FilterActivity.this);

                // set title
                builder.setTitle("Select Languages");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(Constants.LANGUAGES_AVAILABLE, selectedLanguages, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            languagesList.add(i);
                            // Sort array list
                            Collections.sort(languagesList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            languagesList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();

                        stringBuilder.append("language=");
                        // use for loop
                        for (int j = 0; j < languagesList.size(); j++) {
                            // concat array value
                            stringBuilder.append(Constants.LANGUAGES_ABBREVIATIONS[languagesList.get(j)]);
                            // check condition
                            if (j != languagesList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(",");
                            }
                        }
                        // set text on textView
                        languagesTextView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLanguages.length; j++) {
                            // remove all selection
                            selectedLanguages[j] = false;
                            // clear language list
                            languagesList.clear();
                            // clear text view value
                            languagesTextView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
        if(FilterSettings.getLanguagesFilter() != null) languagesTextView.setText(FilterSettings.getLanguagesFilter());

        categoriesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FilterActivity.this);

                // set title
                builder.setTitle("Select Categories");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(Constants.CATEGORIES_AVAILABLE, selectedCategories, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            categoriesList.add(i);
                            // Sort array list
                            Collections.sort(categoriesList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            categoriesList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("category=");
                        // use for loop
                        for (int j = 0; j < categoriesList.size(); j++) {
                            // concat array value
                            stringBuilder.append(Constants.CATEGORIES_AVAILABLE[categoriesList.get(j)].toLowerCase());
                            // check condition
                            if (j != categoriesList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(",");
                            }
                        }
                        // set text on textView
                        categoriesTextView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedCategories.length; j++) {
                            // remove all selection
                            selectedCategories[j] = false;
                            // clear language list
                            categoriesList.clear();
                            // clear text view value
                            categoriesTextView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
        if(FilterSettings.getCategoriesFilter() != null) categoriesTextView.setText(FilterSettings.getCategoriesFilter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_home_screen, menu);
        moreFilters = menu.findItem(R.id.mniMoreFilters);
        help = menu.findItem(R.id.mniHelp);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.mniMoreFilters).setVisible(false);
        menu.findItem(R.id.mniHelp).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.mniHelp){
            //Todo: Set Activity to Help Activity
            Intent intent = new Intent(FilterActivity.this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        else{
            Toast.makeText(this, "Menu item not available", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateFilters() {
        FilterSettings.setCategoriesFilter(categoriesTextView.getText().toString());
        FilterSettings.setCountriesFilter(countriesTextView.getText().toString());
        FilterSettings.setLanguagesFilter(languagesTextView.getText().toString());
    }
}
