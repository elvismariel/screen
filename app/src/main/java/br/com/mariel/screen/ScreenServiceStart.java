package br.com.mariel.screen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;

public class ScreenServiceStart extends Service {

    public ScreenServiceStart() {
        Log.i("ScreenService", "ScreenServiceStart()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ScreenService", "ScreenServiceStart - onCreate()");
        ScreenService service = new ScreenService(this);
        new Timer().schedule(service, 0, 60000); // 8 min;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
        //return (super.onStartCommand(intent, flags, startId));
    }
}
