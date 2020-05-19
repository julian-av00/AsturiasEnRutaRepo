package com.proyecto.asturiasenruta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ContadorPasosActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    TextView txt_pasos;
    boolean funcionando =  false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_pasos);

        txt_pasos = (TextView) findViewById(R.id.idPasos);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        funcionando = true;
        Sensor contadorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(contadorSensor != null){
            sensorManager.registerListener(this, contadorSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        funcionando = false;
    }

    @Override
    protected void onStop(){
        super.onStop();
        funcionando = false;
        txt_pasos.setText("0");

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(funcionando){
            txt_pasos.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
