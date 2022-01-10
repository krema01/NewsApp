package com.example.canteenchecker.mynews.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.canteenchecker.mynews.R;
import com.example.canteenchecker.mynews.core.Constants;

public class LoginActivity extends AppCompatActivity {
    EditText loginApi;
    Button loginApplyButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new Constants(this);



        //SharedPreferences.Editor editor = settings.edit();
        //editor.remove("api");
        //editor.apply();

        //Log.e("LOGIN LOG: ", " " + settings.getString("api", null));


        //Log.e("LOGIN: ", " " + settings.getString("api", null));
        if(Constants.API != null) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else{

            loginApi = findViewById(R.id.loginApi);


            loginApplyButton = findViewById(R.id.loginApplyButton);
            loginApplyButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(loginApi.getText() != null && loginApi.getText().toString().length() > 0){
                        Constants.setAPI(loginApi.getText().toString());

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }

    }
}









//            SharedPreferences.Editor editor = settings.edit();
//            editor.putString("api", "pub_3299d32b8b154373c88df9cbebb156b295d3");
//            editor.apply();
//        }
//        else{ //Switching to Home-Activity





