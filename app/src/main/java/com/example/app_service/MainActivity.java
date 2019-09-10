package com.example.app_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.app_service.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startService;
    Button stopService;
    TextView textView;
    Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService= (Button)findViewById(R.id.startService);
         stopService= (Button)findViewById(R.id.stopService);
          textView= (TextView) findViewById(R.id.randomdisplay);


          startService.setOnClickListener(this);
          stopService.setOnClickListener(this);
          textView.setOnClickListener(this);

          serviceIntent = new Intent(this, BoundService.class);


    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.startService :{

                startService(serviceIntent);
            } break;

            case R.id.stopService: {

                stopService(serviceIntent);

            } break;
        }


    }


}
