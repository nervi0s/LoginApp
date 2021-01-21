package com.nervi.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Map;

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

        String user = this.user.getText().toString();
        String pass = this.pass.getText().toString();

        if (user.isEmpty() || pass.isEmpty() || (!registerSelected && !loginSelected)) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
            System.out.println("Debe rellenar todos los campos");
        } else {

            SharedPreferences sp = getSharedPreferences("database", Context.MODE_PRIVATE);

            String[] users = sp.getAll().keySet().toArray(new String[0]);

            if (registerSelected) {
                if (existUser(user, users)) {
                    Toast.makeText(this, "El usuario ya está registrado", Toast.LENGTH_SHORT).show();
                    System.out.println("El usuario ya está registrado");
                } else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(user, hashPass(pass));
                    editor.apply(); // .commit();

                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    System.out.println("Usuario registrado correctamente");
                }
            } else {
                if (!existUser(user, users)) {
                    Toast.makeText(this, "El usuario introducido no existe", Toast.LENGTH_SHORT).show();
                    System.out.println("El usuario introducido no existe");
                } else {
                    if (isPassCorrect(user, hashPass(pass), sp.getAll())) {
                        Intent i = new Intent(this, Logged.class);
                        i.putExtra("userName", user);
                        startActivity(i);
                    } else {
                        Toast.makeText(this, "La contraseña no es correcta", Toast.LENGTH_SHORT).show();
                        System.out.println("La contraseña no es correcta");
                    }
                }
            }
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

    private boolean isPassCorrect(String user, String pass, Map<String, ?> dataBase) {
        String hashedPass = (String) dataBase.get(user);
        return hashedPass.equals(pass);
    }

    private String hashPass(String pass) {
        return pass;
    }

    //ToDo create method hashPass()
    //Todo check Toast
}