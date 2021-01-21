package com.nervi.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText pass;

    private RadioButton reg;
    private RadioButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.inputUser);
        pass = findViewById(R.id.inputPass);

        reg = findViewById(R.id.radioReg);
        login = findViewById(R.id.radioLogin);

    }

    public void lanzar(View v) {
        boolean registerSelected = reg.isChecked();
        boolean loginSelected = login.isChecked();

        //ToDo check all camps and radio buttons must be full

        SharedPreferences sp = getSharedPreferences("database", Context.MODE_PRIVATE);

        String[] users = sp.getAll().keySet().toArray(new String[0]);
        String user = this.user.getText().toString();
        String pass = this.pass.getText().toString();

        if (registerSelected) {
            if (existUser(user, users)) {
                Toast.makeText(this, "El usuario ya está registrado", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(user, hashPass(pass));
                editor.apply(); // .commit();

                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            }
        } else {

        }

    }

    private boolean existUser(String key, String[] users) {
        for (String user : users) {
            if (user.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private String hashPass(String pass) {
        return null;
    }
}