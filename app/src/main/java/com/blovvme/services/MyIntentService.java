package com.blovvme.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;


public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.d(TAG,"onHandIntent" + Thread.currentThread());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG,"MyIntentService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG,"onDestroy");
    }



}//
