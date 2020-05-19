package com.proyecto.asturiasenruta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class CronometroActivity extends AppCompatActivity {
    private Chronometer cronometro;
    private long pauseOffset;
    private boolean funcionando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        cronometro = findViewById(R.id.cronometro);

    }

    public void startChronometer(View v){
        if(!funcionando){
            cronometro.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            cronometro.start();
            funcionando = true;
        }
    }
    public void pauseChronometer(View v){

        if(funcionando){
            cronometro.stop();
            pauseOffset = SystemClock.elapsedRealtime() - cronometro.getBase();
            funcionando = false;
        }
    }
    public void resetChronometer(View v){

        cronometro.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;

    }
}
