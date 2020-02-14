package com.roberto.delegadin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class LoginActivity extends AppCompatActivity {
    Toolbar barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Obtengo la posicion del elemento que se ha clickado
        //posicion=getIntent().getExtras().getInt("posicion");
        //Añadir la flecha hacia atras
        barra=findViewById(R.id.toolbariniciosesion);
        //Establezco el titulo
        barra.setTitle("INICIO SESION");
        setSupportActionBar(barra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case android.R.id.home:

                finish();
                break;
        }
        return true;
    }

}
