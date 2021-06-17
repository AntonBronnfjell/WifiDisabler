package com.example.wifidisabler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageViewWifi = findViewById(R.id.wifi_img);
        TextView textViewWifiDescription = findViewById(R.id.wifi_description);
        TextView textViewWifiStatus = findViewById(R.id.wifi_status);

        ImageView imageViewLAN = findViewById(R.id.lan_img);
        TextView textViewLANDescription = findViewById(R.id.lan_description);
        TextView textViewLANStatus = findViewById(R.id.lan_status);

        Switch switchWifi = findViewById(R.id.switchWifi);

        TextView textViewVersion = findViewById(R.id.version_status);

        if (isWifiConnected()) {
            imageViewWifi.setImageResource(R.drawable.ic_wifi_on);
            textViewWifiDescription.setText(R.string.wifi_status_active);
            textViewWifiStatus.setText(R.string.service_status_on);
        }
        else {
            imageViewWifi.setImageResource(R.drawable.ic_wifi_off);
            textViewWifiDescription.setText(R.string.wifi_status_disabled);
            textViewWifiStatus.setText(R.string.service_status_off);
        }

        if (isEthernetConnected()) {
            imageViewLAN.setImageResource(R.drawable.lan_active);
            textViewLANDescription.setText(R.string.lan_status_active);
            textViewLANStatus.setText(R.string.service_status_on);
        }
        else {
            imageViewLAN.setImageResource(R.drawable.disable);
            textViewLANDescription.setText(R.string.lan_status_disabled);
            textViewLANStatus.setText(R.string.service_status_off);
        }

        refreshSwitch();

        textViewVersion.setText(BuildConfig.VERSION_NAME);
    }

    public void disableWifi(View view) {
        TextView textView = findViewById(R.id.textViewLog);
        textView.setText("Deactivating Wifi...");

        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);

        if (!isWifiConnected()) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Deactivated!");
                }
            }, 1000);
        }
        else {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Failed!");
                }
            }, 1000);
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Waiting...");
            }
        }, 2000);
    }

    public void refreshCardLAN (View view) {
        ImageView imageViewLAN = findViewById(R.id.lan_img);
        TextView textViewLANDescription = findViewById(R.id.lan_description);
        TextView textViewLANStatus = findViewById(R.id.lan_status);

        ProgressBar progressBar = findViewById(R.id.progressBarLAN);
        progressBar.setVisibility(View.VISIBLE);
        int image;

        TextView textView = findViewById(R.id.textViewLog);
        textView.setText("Checking LAN...");

        if (isEthernetConnected()) {
            image = R.drawable.lan_active;
            textViewLANDescription.setText(R.string.lan_status_active);
            textViewLANStatus.setText(R.string.service_status_on);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText("LAN is active.");
                }
            }, 1000);
        }
        else {
            image = R.drawable.disable;
            textViewLANDescription.setText(R.string.lan_status_disabled);
            textViewLANStatus.setText(R.string.service_status_off);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText("LAN is disabled.");
                }
            }, 1000);
        }

        final Animation anim_out = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                imageViewLAN.setImageResource(image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                imageViewLAN.startAnimation(anim_in);
            }
        });
        imageViewLAN.startAnimation(anim_out);
        progressBar.setVisibility(View.INVISIBLE);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Waiting...");
            }
        }, 2000);
    }

    public void refreshCardWifi (View view) {
        ImageView imageViewWifi = findViewById(R.id.wifi_img);
        TextView textViewWifiDescription = findViewById(R.id.wifi_description);
        TextView textViewWifiStatus = findViewById(R.id.wifi_status);

        ProgressBar progressBar = findViewById(R.id.progressBarWifi);
        progressBar.setVisibility(View.VISIBLE);
        int image;

        TextView textView = findViewById(R.id.textViewLog);
        textView.setText("Checking Wifi...");

        if (isWifiConnected()) {
            image = R.drawable.ic_wifi_on;
            textViewWifiDescription.setText(R.string.wifi_status_active);
            textViewWifiStatus.setText(R.string.service_status_on);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Wifi is active.");
                }
            }, 1000);
        }
        else {
            image = R.drawable.ic_wifi_off;
            textViewWifiDescription.setText(R.string.wifi_status_disabled);
            textViewWifiStatus.setText(R.string.service_status_off);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Wifi is disabled.");
                }
            }, 1000);
        }

        final Animation anim_out = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                imageViewWifi.setImageResource(image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                imageViewWifi.startAnimation(anim_in);
            }
        });
        imageViewWifi.startAnimation(anim_out);
        progressBar.setVisibility(View.INVISIBLE);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Waiting...");
            }
        }, 2000);
    }

    public void serviceSwitch (View view) {
        Switch switchService = findViewById(R.id.switchWifi);
        WifiDisablerApplication wifiDisablerApplication = new WifiDisablerApplication();

        switchService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(!isMyServiceRunning(Disabler.class)) {
                        wifiDisablerApplication.Start();
                    }
                    refreshSwitch();
                }
                else {
                    if(isMyServiceRunning(Disabler.class)) {
                        wifiDisablerApplication.Stop();
                    }
                    refreshSwitch();
                }
            }
        });
    }

    public void refreshCardVersion (View view) {
        ImageView imageViewVersion = findViewById(R.id.version_img);
        //TextView textViewVersionDescription = findViewById(R.id.version_description);

        ProgressBar progressBar = findViewById(R.id.progressBarVersion);
        progressBar.setVisibility(View.VISIBLE);
        int image = R.drawable.ic_launcher_foreground;

        TextView textView = findViewById(R.id.textViewLog);
        textView.setText("Checking Version...");

        final Animation anim_out = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                imageViewVersion.setImageResource(image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                imageViewVersion.startAnimation(anim_in);
            }
        });

        new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.JSON)
                .setUpdateJSON("https://raw.githubusercontent.com/javiersantos/AppUpdater/master/app/update-changelog.json")
                .setTitleOnUpdateAvailable("Update available")
                .setContentOnUpdateAvailable("Check out the latest version available of my app!")
                .setTitleOnUpdateNotAvailable("Update not available")
                .setContentOnUpdateNotAvailable("No update available. Check for updates again later!")
                .setButtonUpdate("Update now?")
	            .setButtonDismiss("Maybe later")
                .setButtonDoNotShowAgain("Huh, not interested")
	            .setIcon(R.drawable.ic_update) // Notification icon
                .setCancelable(false) // Dialog could not be dismissable
                .start();

        imageViewVersion.startAnimation(anim_out);
        progressBar.setVisibility(View.INVISIBLE);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Waiting...");
            }
        }, 2000);
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private Boolean isWifiConnected(){
        if(isNetworkAvailable()){
            ConnectivityManager cm
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            return (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI);
        }
        return false;
    }

    private Boolean isEthernetConnected(){
        if(isNetworkAvailable()){
            ConnectivityManager cm
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            return (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_ETHERNET);
        }
        return false;
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

    private void refreshSwitch() {
        Switch switchService = findViewById(R.id.switchWifi);
        TextView textView = findViewById(R.id.textViewLog);

        if (isMyServiceRunning(Disabler.class)) {
            switchService.setChecked(true);
            textView.setText("Service active...");
        }
        else {
            switchService.setChecked(false);
            textView.setText("Service disabled...");
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Waiting...");
            }
        }, 2000);
    }
}