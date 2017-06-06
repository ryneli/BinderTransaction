package com.zqli.bindertransaction;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by zhenqiangli on 6/6/17.
 */

public class LocalService extends Service {
    private String TAG = "LocalService";
    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    // Random number generator
    private final Random generator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            Log.d(TAG, "getService: ");
            return LocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return binder;
    }

    /** method for clients */
    public int getRandomNumber() {
        Log.d(TAG, "getRandomNumber: ");
        return generator.nextInt(100);
    }
}
