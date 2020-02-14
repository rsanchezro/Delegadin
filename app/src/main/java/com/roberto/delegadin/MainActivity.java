package com.roberto.delegadin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout midrawer;
    NavigationView minavview;
    Toolbar barra;
    ActionBarDrawerToggle toggle;


    public boolean sesioniniciada=false;

    AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar mitoolbar=findViewById(R.id.mitoolbar);
        setSupportActionBar(mitoolbar);
        midrawer=findViewById(R.id.midrawer);
        minavview=findViewById(R.id.minavigationview);

        //Obtengo la cabecera para cambiar el nombre del usuario
        View cabecera=minavview.getHeaderView(0);

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,new CandidatosFragment(),"candidatos");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((TextView)(cabecera.findViewById(R.id.usuario_textview_cabecera))).setText("ENTRAR");

        //Asocio a la imagen el inicio de session

        cabecera.findViewById(R.id.imagenAvatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivityForResult(i,1);
            }
        });

        //SE PODRIA PERFECTAMENTE PREPARAR (INFLAR) OTRO MENU AL NAVIGATIONVIEW, en funcion
        //por ejemplo del tipo de usuario que establezca conexion, para hacerlo


        //Limpio el menu que tenga establecido el navview

        // minavview.getMenu().clear();
        //Inflo el nuevo menu
        // minavview.inflateMenu(identificador del recurso menu a inflar);
        //VER DOCUMENTACION https://stackoverflow.com/questions/32649333/changing-navigation-drawer-items-at-runtime
        //Aqui preparo el menu


        //Para que aparece el boton menu hamburguesa y automaticamente se abra el menu lateral
        toggle=new ActionBarDrawerToggle(this,midrawer,barra,R.string.drawer_open,R.string.drawer_close);



        midrawer.addDrawerListener(toggle);
        //Preparo la navegacion del menu
        //Construimos una configuración de la Barra, para asociarsela al drawerlayout, indico
        //las pantallas de navegacion que tienen que tener el mismo valor que el id del menu

        //Con esta función, establecemos vinculación del ActionBar con el Controlador de navegacion, no
        //es imprescindible
        //    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //Vincula el controlador con el menu de opciones
        toggle.setDrawerIndicatorEnabled(true);
        toggle.setDrawerSlideAnimationEnabled(true);
        toggle.syncState();



        minavview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                int contenedor=R.id.contenedor;
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.candidatos:
                        fragment=new CandidatosFragment();

                        break;
                    case R.id.resultados:
                       fragment=new ResultadoEleccionesFragment();
                        break;
                    case R.id.votar:
                     fragment=new VotarFragment();
                        break;
                    case R.id.cerrarsesion:
                       /*


                        */
                        break;

                }
                if(fragment!=null)
                {
                    getSupportFragmentManager().beginTransaction().replace(contenedor,fragment,"fragmento").commit();
                }

                menuItem.setChecked(true);
                midrawer.closeDrawer(GravityCompat.START);
                return true;
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            midrawer.openDrawer(GravityCompat.START);

        }
        return true;

    }



}
