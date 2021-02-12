package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  private CardView currencyDetector,roomLightDetector,documentReader,settings;
  private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //text to speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS)
                {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        //creating variable
        currencyDetector = (CardView)findViewById(R.id.currency);
        roomLightDetector = (CardView)findViewById(R.id.roomlight);
        documentReader = (CardView)findViewById(R.id.reader);
        settings = (CardView)findViewById(R.id.settings);


        currencyDetector.setOnClickListener(this);
        roomLightDetector.setOnClickListener(this);
        documentReader.setOnClickListener(this);
        settings.setOnClickListener(this);

    }
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.currency:
                String s1 ="opening Currency Detector";
                int speech1 = textToSpeech.speak(s1,TextToSpeech.QUEUE_FLUSH,null);
                i= new Intent(this,CurrencyDetector.class);
                startActivity(i);
                break;


            case R.id.roomlight:
                String s2 ="opening Room Light Detector";
                int speech2 = textToSpeech.speak(s2,TextToSpeech.QUEUE_FLUSH,null);
                i= new Intent(this,RoomLightDetector.class);
                startActivity(i);
                break;

            case R.id.reader:
                String s3 ="opening Document Reader";
                int speech3 = textToSpeech.speak(s3,TextToSpeech.QUEUE_FLUSH,null);
                i= new Intent(this,DocumentReader.class);
                startActivity(i);
                break;

            case R.id.settings:
                String s4 ="opening Settings";
                int speech4 = textToSpeech.speak(s4,TextToSpeech.QUEUE_FLUSH,null);
                i= new Intent(this,Settings.class);
                startActivity(i);
                break;


        }
    }

}