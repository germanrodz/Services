package com.blovvme.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class MyBoundService extends Service {
    public MyBoundService() {
    }
    private static final String TAG = "MyBoundService";

    IBinder iBinder = new MyBinder();

    String passed;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }


    @Override
    public IBinder onBind(Intent intent) {
       Log.d(TAG,"onBind");
        passed = intent.getStringExtra("text");
        return iBinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);

    }

    public class MyBinder extends Binder {

        MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    public ArrayList<String> getRandomData(){
        Random random = new Random();
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < Integer.parseInt(passed) ; i++){
            list.add(new BigInteger(130, random).toString(32));
        }
        return list;
    }



}
