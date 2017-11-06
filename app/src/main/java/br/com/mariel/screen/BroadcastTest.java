package br.com.mariel.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by fmanager on 02/10/17.
 */

public class BroadcastTest extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ScreenService", "BroadcastTest - onReceive()");
        intent = new Intent("SCREEN_SERVICE_START");
        context.startService(intent);
    }
}
