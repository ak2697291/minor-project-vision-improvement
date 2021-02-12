package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CurrencyDetector extends AppCompatActivity {

    private ImageView CameraPicture;
    private Button Capture,Process;
    private static final int REQUEST_IMAGE_CAPTURE =101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_detector);
        CameraPicture = findViewById(R.id.cameraPicture);
        Capture = findViewById(R.id.takePicture);
        Process = findViewById(R.id.process);

    }

    public void takePicture(View view) {
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(imageTakeIntent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            CameraPicture.setImageBitmap(imageBitmap);
        }


    }

    public void processPicture(View view) {
    }
}