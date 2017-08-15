package com.blovvme.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.IntDef;

import static android.provider.Settings.*;

public class MyService extends Service {
    public MyService() {
    }

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
    }
}
