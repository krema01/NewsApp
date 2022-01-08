package com.example.canteenchecker.mynews.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;
import com.example.canteenchecker.mynews.core.FilterSettings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatesActivity extends AppCompatActivity {

    final Calendar myCalendarStart= Calendar.getInstance();
    final Calendar myCalendarEnd= Calendar.getInstance();

    TextView datesStartFilter, datesEndFilter;
    Button saveButton, deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        TextView datesStartFilter = findViewById(R.id.datesStartFilter);
        TextView datesEndFilter = findViewById(R.id.datesEndFilter);

        //Start Date
        DatePickerDialog.OnDateSetListener dateFrom = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH,month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH,day);
                updateLabelFrom();
            }
        };
        datesStartFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DatesActivity.this, dateFrom, myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH),myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        if(FilterSettings.getDateFromFilter() != null) datesStartFilter.setText(FilterSettings.getDateFromFilter());

        //End Date
        DatePickerDialog.OnDateSetListener dateTo =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH,month);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH,day);
                updateLabelTo();
            }
        };
        datesEndFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DatesActivity.this, dateTo, myCalendarEnd.get(Calendar.YEAR),
                        myCalendarEnd.get(Calendar.MONTH),myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        if(FilterSettings.getDateToFilter() != null) datesEndFilter.setText(FilterSettings.getDateToFilter());


    }


    private void setButtons() {
        //Button saveButton
        saveButton = findViewById(R.id.save_dates_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("SET DATES: ", "DATE FROM: " + datesStartFilter.getText().toString());
                Log.e("SET DATES: ", "DATE TO: " + datesEndFilter.getText().toString());

                Intent intent = new Intent(DatesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });

        //Button deleteButton
        deleteButton = findViewById(R.id.delete_countries_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("SET DATES: ", "DATE FROM: " + datesStartFilter.getText().toString());
                Log.e("SET DATES: ", "DATE TO: " + datesEndFilter.getText().toString());

                Intent intent = new Intent(DatesActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateLabelFrom(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
       datesStartFilter.setText("from_date=" + dateFormat.format(myCalendarStart.getTime()));
    }
    private void updateLabelTo(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        datesEndFilter.setText("to_date=" +dateFormat.format(myCalendarEnd.getTime()));
    }

}
