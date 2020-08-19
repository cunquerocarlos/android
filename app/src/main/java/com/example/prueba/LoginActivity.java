package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        final EditText username, password;
        Button btnLogin = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int emailLength = username.getText().length() ;
                int passwordLength = password.getText().length();
                if(emailLength == 0)
                    Toast.makeText(LoginActivity.this, "Ingrese usuario", Toast.LENGTH_SHORT).show();
                else if (passwordLength == 0)
                    Toast.makeText(LoginActivity.this, "Ingrese contraseña", Toast.LENGTH_SHORT).show();
                else if(emailLength < 8)
                    Toast.makeText(LoginActivity.this, "El usuario debe tener al menos ocho caracteres ", Toast.LENGTH_SHORT).show();
                else if (passwordLength < 6)
                    Toast.makeText(LoginActivity.this, "La contraseña debe tener la menos seis caracteres", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(),StatementActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }



}