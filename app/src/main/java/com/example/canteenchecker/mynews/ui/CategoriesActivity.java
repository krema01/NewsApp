package com.example.canteenchecker.mynews.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class CategoriesActivity extends AppCompatActivity {

    static ArrayList<String> selectedCategories = new ArrayList<String>();
    LinearLayout layout;
    Button saveButton, deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        layout = findViewById(R.id.categoriesFilterLayout);

        restoreFilters();
        setCategories();

        setButtons();

    }

    private void restoreFilters() {
        selectedCategories = (ArrayList) FilterSettings.getCategoriesFullName();
        Log.e("SelectedCategories: ", "Categories: " + selectedCategories.toString());
    }


    private void setCategories() {
        for(int i = 0; i < Constants.CATEGORIES.length; ++i){
            TextView textView = new TextView(this);
            textView.setText(Constants.CATEGORIES[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            textView.setLayoutParams(params);
            textView.setTextSize(25);
            if(selectedCategories != null) {
                if (selectedCategories.contains(Constants.CATEGORIES[i]))
                    textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
            }
            else
                textView.setBackgroundColor(Color.parseColor("#ffffff"));

            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String selectedCategory = textView.getText().toString();
                    if (selectedCategories != null && selectedCategories.contains(selectedCategory)) {
                        Log.e("ALREADY SELECTED", selectedCategory);
                        //Category already selected.. unselect
                        selectedCategories.remove(selectedCategory);
                        textView.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    else{
                        Log.e("NOT SELECTED YET", selectedCategory);
                        if(selectedCategories != null && selectedCategories.size() < 5) {
                            //select category
                            selectedCategories.add(selectedCategory);
                            textView.setBackgroundColor(Color.parseColor("#a3a3a3"));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Please unselect another category first!\nYou have already selected 5 categories.",
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
        //Button saveButton
        saveButton = findViewById(R.id.save_categories_button);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(selectedCategories.size() > 0){
                    StringBuilder categoryShortSb = new StringBuilder();
                    ArrayList<String> categoryLong = new ArrayList<>();
                    categoryShortSb.append("category=");
                    int i = 0;
                    for(String category : selectedCategories){
                        categoryShortSb.append(category);
                        categoryLong.add(category);
                        if (i != selectedCategories.size() - 1) {
                            categoryShortSb.append(",");
                        }
                        ++i;
                    }
                    FilterSettings.setCategoriesFilter(categoryShortSb.toString());
                    FilterSettings.setCategoriesFullName(categoryLong);
                }else {
                    FilterSettings.setCategoriesFilter("");
                    FilterSettings.setCategoriesFullName(null);
                }
                Intent intent = new Intent(CategoriesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

        //Button deleteButton
        deleteButton = findViewById(R.id.delete_categories_button);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(int i = 0; i < layout.getChildCount(); ++i){
                    layout.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                selectedCategories.clear();
                FilterSettings.setCategoriesFilter("");
                FilterSettings.setCategoriesFullName(null);

                Intent intent = new Intent(CategoriesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

    }

}
