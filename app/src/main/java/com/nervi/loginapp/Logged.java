package com.nervi.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Logged extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        TextView userName = findViewById(R.id.username);

        Bundle bundle = getIntent().getExtras();

        userName.setText(bundle.getString("userName"));
    }
}