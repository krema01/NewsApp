package com.example.canteenchecker.mynews.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;

public class HelpActivity extends AppCompatActivity {

    private MenuItem moreFilters;
    private MenuItem help;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        EditText apiChangeText = findViewById(R.id.helpChangeApi);
        Button apiChangeButton = findViewById(R.id.helpChangeApiButton);
        apiChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiChangeText.getText().toString().length() > 0) {
                    SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.API, 0);

                    SharedPreferences.Editor editor = settings.edit();
                    editor.remove("api");
                    editor.putString("api", apiChangeText.getText().toString());
                    Constants.API = apiChangeText.getText().toString();
                    editor.apply();
                    Intent intent = new Intent(HelpActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
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
        menu.findItem(R.id.mniMoreFilters).setVisible(true);
        menu.findItem(R.id.mniHelp).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mniMoreFilters) {
            //Todo: Set Activity to Filters Activity
            Intent filterIntent = new Intent(HelpActivity.this, FilterActivity.class);
            startActivity(filterIntent);
            return true;
        } else {
            Toast.makeText(this, "Menu item not available", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}