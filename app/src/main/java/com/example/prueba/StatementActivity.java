package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;


public class StatementActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statement_activity);

        TextView username = findViewById(R.id.username);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Button btnContinue = findViewById(R.id.btn_continue);
        username.setText("Bienvenido, " + Objects.requireNonNull(getIntent().getExtras()).getString("username", ""));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.item_logout)
                {
                   Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                   startActivity(intent);
                }
                return true;
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentStatement fragment = new FragmentStatement();
        fragmentTransaction.add(R.id.container_fragment, fragment);
        fragmentTransaction.commit();
    }

}