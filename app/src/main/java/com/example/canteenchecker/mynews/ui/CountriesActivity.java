package com.example.canteenchecker.mynews.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;
import com.example.canteenchecker.mynews.core.FilterSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountriesActivity extends AppCompatActivity {
    static ArrayList<String> selectedCountries = new ArrayList<String>();
    LinearLayout layout;
    Button saveButton, deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        layout = findViewById(R.id.countriesFilterLayout);

        restoreFilters();
        setCountries();

        setButtons();

    }

    private void restoreFilters() {
        selectedCountries = (ArrayList)FilterSettings.getCountriesFullName();
        Log.e("SELECTED IN COUNTRIES: ", " " + selectedCountries);
    }


    private void setCountries() {
        for(Map.Entry<String,String> entry : Constants.COUNTRIES.entrySet()){

            LinearLayout countryLayout = new LinearLayout(this);
            countryLayout.setOrientation(LinearLayout.HORIZONTAL);
            countryLayout.setWeightSum(5);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.baseline_check_circle_green_800_24dp);
            imageView.setVisibility(ImageView.INVISIBLE);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            imageParams.gravity = Gravity.CENTER;
            imageParams.setMargins(10,10,10,10);
            imageView.setLayoutParams(imageParams);




            TextView textView = new TextView(this);
            textView.setText(entry.getKey());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            textView.setLayoutParams(params);
            textView.setTextSize(20);

            countryLayout.addView(imageView);
            countryLayout.addView(textView);

            if(selectedCountries != null) {
                if (selectedCountries.contains(entry.getKey()))
                    imageView.setVisibility(ImageView.VISIBLE);
                    //textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
            }
            //else
            //    textView.setBackgroundColor(Color.parseColor("#ffffff"));

            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String selectedCountry = textView.getText().toString();
                    if (selectedCountries != null && selectedCountries.contains(selectedCountry)) {
                        Log.e("ALREADY SELECTED", selectedCountry);
                        //Country already selected.. unselect
                        selectedCountries.remove(selectedCountry);
                        imageView.setVisibility(ImageView.INVISIBLE);
                        //textView.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    else{
                        Log.e("NOT SELECTED YET", selectedCountry);
                        if(selectedCountries != null && selectedCountries.size() < 5) {
                            //select country
                            selectedCountries.add(selectedCountry);
                            imageView.setVisibility(ImageView.VISIBLE);
                            //textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Please unselect another country first!\nYou have already selected 5 countries.",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                }
            });
            layout.addView(countryLayout);
        }
    }

    private void setButtons() {
        //Button saveButton
        saveButton = findViewById(R.id.save_countries_button);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(selectedCountries.size() > 0){
                    StringBuilder countryShortSb = new StringBuilder();
                    ArrayList<String> countryLong = new ArrayList<>();
                    countryShortSb.append("country=");
                    int i = 0;
                    for(String country : selectedCountries){
                        countryShortSb.append(Constants.COUNTRIES.get(country));
                        countryLong.add(country);
                        if (i != selectedCountries.size() - 1) {
                            countryShortSb.append(",");
                        }
                        ++i;
                    }
                    FilterSettings.setCountriesFilter(countryShortSb.toString());
                    FilterSettings.setCountriesFullName(countryLong);
                }else {
                    FilterSettings.setCountriesFilter("");
                    FilterSettings.setCountriesFullName(null);
                }
                Intent intent = new Intent(CountriesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

        //Button deleteButton
        deleteButton = findViewById(R.id.delete_countries_button);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selectedCountries.clear();
                FilterSettings.setCountriesFilter("");
                FilterSettings.setCountriesFullName(null);

                Intent intent = new Intent(CountriesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

    }
}


