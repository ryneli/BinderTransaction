package com.zqli.bindertransaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LocalService localService;
    boolean isBound;

    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to local service
        Log.d(TAG, "onStart: ");
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        if (isBound) {
            int num = localService.getRandomNumber();
            Toast.makeText(this, "number:" + num, Toast.LENGTH_LONG).show();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            localService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            isBound = false;
        }
    };
}
