package com.blovvme.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    MyBoundService myBoundService;
    private static final String TAG = "MySecondActiviy";
    boolean isBoundConnected = true;

    TextView readSum;

    ArrayList<String> randomStringList;
    RecyclerView rvRandomStrings;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RecyclerView.Adapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String p = intent.getStringExtra("text");
        readSum = (TextView) findViewById(R.id.readSum);
        Intent boundIntent = new Intent(this, MyBoundService.class);
        boundIntent.putExtra("text",p);
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        //recyclerview here
        rvRandomStrings = (RecyclerView) findViewById(R.id.rvRandomStrings);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvRandomStrings.setLayoutManager(layoutManager);
        rvRandomStrings.setItemAnimator(itemAnimator);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnectedStarted");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
           randomStringList =  myBoundService.getRandomData();
            //readSum.setText(String.valueOf(myBoundService.getRandomData()));
            isBoundConnected = true;
            //adapter
            mAdapter = new MainAdapter(randomStringList);
            rvRandomStrings.setAdapter(mAdapter);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "Service disconnected");
            isBoundConnected = false;
        }
    };

}
