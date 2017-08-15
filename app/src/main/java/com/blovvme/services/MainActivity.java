package com.blovvme.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //for bound service
    MyBoundService myBoundService;
    private static final String TAG = "MyService";
    boolean isBoundConnected =true;
    Button btnSendBound,btnStartSound,btnStopSound;
    TextView txtBound;
    EditText etText;


    //Button btnStartNormalService,btnStopNormalService,btnStartIntentService,btnBindService,btnUnbindService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBound = (Button)findViewById(R.id.btnSendBound);
//        txtBound = (TextView) findViewById(R.id.txtBound);
       etText = (EditText) findViewById(R.id.etText);

    }

    public void startServices(View view) {
        Intent normalIntent =  new Intent(this, MyNormalService.class);
        Intent inIntent = new Intent(this, MyIntentService.class);
        Intent boundIntent = new Intent(this, MyBoundService.class);
        Intent intent = new Intent(this,SecondActivity.class);
        //Intent musicIntent = new Intent(this,MyService.class);
        switch (view.getId()){

            case R.id.btnStartNormalService:

                normalIntent.putExtra("data","This is a normal service");
                startService(normalIntent);
                break;

            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;

            case R.id.btnStartIntentService:
                inIntent.putExtra("data","This is an intent service");
                startService(inIntent);
                break;
            //for bound service
            case R.id.btnOnBindService:
                bindService(boundIntent,serviceConnection ,Context.BIND_AUTO_CREATE);
                break;
            //for bound service
            case R.id.btnUnBindService:
                unbindService(serviceConnection);
                //isBoundConnected = false;
                break;
            case R.id.btnSendBound:
                intent.putExtra("text", etText.getText().toString());
                startActivity(intent);
                break;

            case R.id.btnStarSound:
                startService(new Intent(this,MyService.class));
                break;

            case R.id.btnStopSound:
                stopService(new Intent(this, MyService.class));
                break;

        }
    }

    ///for bound service
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnectedStarted");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            myBoundService.getRandomData();
            Log.d(TAG, "onServiceConnected: " + myBoundService.getRandomData());
            isBoundConnected =true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"Service disconnected");
            isBoundConnected = false;
        }
    };
}//
