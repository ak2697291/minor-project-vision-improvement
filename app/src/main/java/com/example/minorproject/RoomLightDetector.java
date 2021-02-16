package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import com.example.minorproject.databinding.ActivityRoomLightDetectorBinding;

import java.util.Locale;

public class RoomLightDetector extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    ActivityRoomLightDetectorBinding binding;
    private Sensor light;
    private float maxValue;
    TextToSpeech textToSpeech;
    @Override


    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }




    public final void onSensorChanged(SensorEvent event) {
        float lighting = event.values[0];
        if(lighting>10)
            binding.textView2.setText("There is sufficient light");
        else
            binding.textView2.setText("Please switch on the lights");


        // Do something with this sensor data.

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener( this,light,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRoomLightDetectorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        light=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    int lang=textToSpeech.setLanguage(Locale.US);
                    if(lang==TextToSpeech.LANG_MISSING_DATA||lang==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(RoomLightDetector.this, "Language not supported", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(RoomLightDetector.this, "Language supported", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

        binding.checkLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= binding.textView2.getText().toString();
                textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
            }
        });


    }
    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
