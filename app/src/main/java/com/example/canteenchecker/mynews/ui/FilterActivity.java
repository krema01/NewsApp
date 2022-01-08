package com.example.canteenchecker.mynews.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;
import com.example.canteenchecker.mynews.core.FilterSettings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class FilterActivity extends AppCompatActivity {

    private MenuItem moreFilters;
    private MenuItem help;



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);



        findViewById(R.id.countries_filter_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, CountriesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.languages_filter_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, LanguagesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.categories_filter_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.help_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.filtersSearchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText keywordView = findViewById(R.id.filtersKeyword);

                v.getContext().startActivity(HomeActivity
                        .createIntent(v.getContext(), keywordView.getText().toString()));
            }
        });

        EditText keywordTextView = findViewById(R.id.filtersKeyword);
        keywordTextView.setText(HomeActivity.lastKeyword);
    }


    /*========== Options Menu ==========*/
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
        if (id == R.id.mniHelp) {
            //Todo: Set Activity to Help Activit
            Intent helpIntent = new Intent(FilterActivity.this, HelpActivity.class);
            startActivity(helpIntent);
            return true;
        } else {
            Toast.makeText(this, "Menu item not available", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
