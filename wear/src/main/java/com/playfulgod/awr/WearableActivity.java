package com.playfulgod.awr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.io.IOException;

public class WearableActivity extends Activity implements View.OnClickListener {

        private Button reboot, recovery, bootloader, poweroff;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wearable);
            final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
            stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
                @Override
                public void onLayoutInflated(WatchViewStub stub) {

                    reboot = (Button) findViewById(R.id.reboot);
                    recovery = (Button) findViewById(R.id.recovery);
                    bootloader = (Button) findViewById(R.id.bootloader);
                    poweroff = (Button) findViewById(R.id.poweroff);
                    // set listener
                    reboot.setOnClickListener(WearableActivity.this);
                    recovery.setOnClickListener(WearableActivity.this);
                    bootloader.setOnClickListener(WearableActivity.this);
                    poweroff.setOnClickListener(WearableActivity.this);
                }
            });
        }

        @Override
        public void onClick(View v) { // view handler

            if (v instanceof Button) { // check if button was clicked
                //Intent intentConfirmActivity = new Intent(WearableActivity.this, ConfirmationActivity.class);
                //int animation;
                //String message = "";
                switch (v.getId()) {
                    case R.id.reboot:
                        //animation = ConfirmationActivity.SUCCESS_ANIMATION;
                        //message = "Rebooting";
                        reboot("");
                        break;
                    case R.id.recovery:
                        reboot("recovery");
                        break;
                    case R.id.bootloader:
                        reboot("bootloader");
                        break;
                    case R.id.poweroff:
                        reboot("-p");
                        break;
                    //default:
                        //animation = ConfirmationActivity.FAILURE_ANIMATION;
                        //break;
                }

                //intentConfirmActivity.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, animation); // set animation type
                //intentConfirmActivity.putExtra(ConfirmationActivity.EXTRA_MESSAGE, message); // set message
                //startActivity(intentConfirmActivity); // start animation activity
            }
        }
        private void reboot(String mode) {
            String[] command = new String[]{"su", "-C", "reboot", mode};
            try {
                Process process = new ProcessBuilder(command).start();
            } catch (IOException e) {
            }
        }
    }