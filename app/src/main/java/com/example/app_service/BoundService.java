package com.example.app_service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Random;

/**
 * Created by Olije Favour on 9/7/2019.
 * Copyright (c) 2019    All rights reserved.
 */



public class BoundService extends Service {

 private static final int  COUNT_FLAG =0;
 private static final int MAX =100;
 private static final int MIN =0;
 private  int value;
 private Boolean isServiceRunning;
 private Boolean isServiceBound;


  Messenger messenger =new Messenger(new RemoteService());



    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service >>>", "onBind: ");

        return messenger.getBinder();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service >>>>>> ", "onStartCommand: ");

         isServiceRunning =true;

        new Thread(new Runnable() {
            @Override
            public void run() {

              while(isServiceRunning) {
                  try {

                      Thread.sleep(1000);
                      if(isServiceRunning) {
                          value = new Random().nextInt(MAX) + MIN;

                          Log.d("Service >>>>>> ", "onStartCommand:  " + value);
                      }

                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }

              }

            }
        }).start();

        return START_STICKY;

    }


    @Override
    public void onDestroy() {
        isServiceRunning =false;

        Log.d("Service >>>>>> ", "onDestroy: ");
        super.onDestroy();
    }

    public class RemoteService extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {




            switch (msg.what){
                case COUNT_FLAG:{
                    Message randomNumberMessage = msg.obtain();

                    randomNumberMessage.arg1 =getValue();

                    try {
                        msg.replyTo.send(randomNumberMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }break;
            }

            super.handleMessage(msg);
        }
    }

    public int getValue() {
        return value;
    }

}
