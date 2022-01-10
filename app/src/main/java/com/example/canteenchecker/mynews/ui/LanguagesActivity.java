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
import java.util.Map;

public class LanguagesActivity extends AppCompatActivity {

    static ArrayList<String> selectedLanguages = new ArrayList<String>();
    LinearLayout layout;
    Button saveButton, deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        layout = findViewById(R.id.languagesFilterLayout);

        restoreFilters();
        setLanguages();

        setButtons();

    }

    private void restoreFilters() {
        selectedLanguages = (ArrayList) FilterSettings.getLanguagesFullName();
    }


    private void setLanguages() {
        for(Map.Entry<String,String> entry : Constants.LANGUAGES.entrySet()){

            LinearLayout languageLayout = new LinearLayout(this);
            languageLayout.setOrientation(LinearLayout.HORIZONTAL);
            //languageLayout.setWeightSum(5);

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

            languageLayout.addView(imageView);
            languageLayout.addView(textView);


            if(selectedLanguages != null) {
                Log.e("Sel Lang", " " + selectedLanguages);
                if (selectedLanguages.contains(entry.getKey()))
                    imageView.setVisibility(ImageView.VISIBLE);
                    //textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
            }
            //else
            //    textView.setBackgroundColor(Color.parseColor("#ffffff"));
//
            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String selectedLanguage = textView.getText().toString();
                    if (selectedLanguages != null && selectedLanguages.contains(selectedLanguage)) {
                        Log.e("ALREADY SELECTED", selectedLanguage);
                        //Language already selected.. unselect
                        selectedLanguages.remove(selectedLanguage);
                        imageView.setVisibility(ImageView.INVISIBLE);
                        //textView.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    else{
                        Log.e("NOT SELECTED YET", selectedLanguage);
                        if(selectedLanguages != null && selectedLanguages.size() < 5) {
                            //select language
                            selectedLanguages.add(selectedLanguage);
                            imageView.setVisibility(ImageView.VISIBLE);
                            //textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Please unselect another language first!\nYou have already selected 5 languages.",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                }
            });
            layout.addView(languageLayout);
        }
    }

    private void setButtons() {
        //Button saveButton
        saveButton = findViewById(R.id.save_languages_button);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(selectedLanguages.size() > 0){
                    StringBuilder languageShortSb = new StringBuilder();
                    ArrayList<String> languageLong = new ArrayList<>();
                    languageShortSb.append("language=");
                    int i = 0;
                    for(String language : selectedLanguages){
                        languageShortSb.append(Constants.LANGUAGES.get(language));
                        languageLong.add(language);
                        if (i != selectedLanguages.size() - 1) {
                            languageShortSb.append(",");
                        }
                        ++i;
                    }
                    FilterSettings.setLanguagesFilter(languageShortSb.toString());
                    FilterSettings.setLanguagesFullName(languageLong);
                }else {
                    FilterSettings.setCountriesFilter("");
                    FilterSettings.setCountriesFullName(null);
                }
                Intent intent = new Intent(LanguagesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

        //Button deleteButton
        deleteButton = findViewById(R.id.delete_languages_button);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selectedLanguages.clear();
                FilterSettings.setLanguagesFilter("");
                FilterSettings.setLanguagesFullName(null);

                Intent intent = new Intent(LanguagesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });
    }
}
