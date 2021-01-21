package com.nervi.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.inputUser);
        pass = findViewById(R.id.inputPass);

    }

    public void lanzar(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("database", MODE_PRIVATE);

    }

    private boolean existUser(String key){
        return false;
    }
}