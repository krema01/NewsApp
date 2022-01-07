package com.example.canteenchecker.mynews.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Dimension;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;

import java.net.CookieHandler;
import java.util.ArrayList;

public class CountriesActivity extends AppCompatActivity {
    ArrayList<String> selectedCountries = new ArrayList<String>();
    LinearLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        layout = findViewById(R.id.countriesFilterLayout);

        setCountries();

        setButtons();

    }



    private void setCountries() {
        for(int i = 0; i < Constants.COUNTRIES_AVAILABLE.length; ++i){
            TextView textView = new TextView(this);
            textView.setText(Constants.COUNTRIES_AVAILABLE[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            textView.setLayoutParams(params);
            textView.setTextSize(25);
            textView.setBackgroundColor(Color.parseColor("#ffffff"));
            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String selectedCountry = textView.getText().toString();
                    if(selectedCountries.contains(selectedCountry)){
                        Log.e("ALREADY SELECTED", selectedCountry);
                        //Country already selected.. unselect
                        selectedCountries.remove(selectedCountry);
                        textView.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    else{
                        Log.e("NOT SELECTED YET", selectedCountry);
                        if(selectedCountries.size() < 5) {
                            //select country
                            selectedCountries.add(selectedCountry);
                            textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
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
            layout.addView(textView);
        }
    }

    private void setButtons() {

    }
}


