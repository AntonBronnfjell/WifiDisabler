package com.example.wifidisabler;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class WifiDisablerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!isMyServiceRunning(Disabler.class)) {
            startService(new Intent(this, Disabler.class));
        }
    }

    public void Start() {
        if (!isMyServiceRunning(Disabler.class)) {
            startService(new Intent(this, Disabler.class));
        }
    }

    public void Stop() {
        if (isMyServiceRunning(Disabler.class)) {
            stopService(new Intent(this, Disabler.class));
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
