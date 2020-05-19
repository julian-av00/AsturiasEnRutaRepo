package com.proyecto.asturiasenruta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Codigo para que funcione la SplashScreen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                //Intencion para saber a que pantalla va a pasar despues de mostrar la SplashScreen
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
            }
        },2000); //Tiempo que se va a mostrar en milisegundos
    }
}
