package com.playfulgod.awr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

import java.io.IOException;
import java.io.OutputStreamWriter;


public class HhActivity extends Activity {

    private String TAG = "HhActivity";

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Android Wear Rebooter", "onCreate() does nothing since it's a wear app");

        setContentView(R.layout.activity_hh);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // Reboot Button
        final Button reboot = (Button) findViewById(R.id.reboot);
        reboot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == reboot) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HhActivity.this);
                    builder.setMessage("Are you sure you want to reboot?");
                    builder.setTitle("Confirmation Dialog");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // do something after confirm
                            Runtime runtime = Runtime.getRuntime();
                            Process proc = null;
                            OutputStreamWriter osw = null;

                            String command = "/system/bin/reboot";

                            try { // Run Script

                                proc = runtime.exec("su");
                                osw = new OutputStreamWriter(proc.getOutputStream());
                                osw.write(command);
                                osw.flush();
                                osw.close();

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            Toast.makeText(HhActivity.this, "Rebooting", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.create().show();
                }
            }
        });

        // Reboot to Recovery Button
        final Button recovery = (Button) findViewById(R.id.recovery);
        recovery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == recovery) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HhActivity.this);
                    builder.setMessage("Are you sure you want to reboot to recovery?");
                    builder.setTitle("Confirmation Dialog");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // do something after confirm
                            Runtime runtime = Runtime.getRuntime();
                            Process proc = null;
                            OutputStreamWriter osw = null;

                            String command = "/system/bin/reboot recovery";

                            try { // Run Script

                                proc = runtime.exec("su");
                                osw = new OutputStreamWriter(proc.getOutputStream());
                                osw.write(command);
                                osw.flush();
                                osw.close();

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            Toast.makeText(HhActivity.this, "Rebooting to Recovery", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.create().show();
                }
            }
        });

        // Reboot to Bootloader Button
        final Button bootloader = (Button) findViewById(R.id.bootloader);
        bootloader.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == bootloader) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HhActivity.this);
                    builder.setMessage("Are you sure you want to reboot to bootloader? May not work for all devices!!!");
                    builder.setTitle("Confirmation Dialog");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // do something after confirm
                            Runtime runtime = Runtime.getRuntime();
                            Process proc = null;
                            OutputStreamWriter osw = null;

                            String command = "/system/bin/reboot bootloader";

                            try { // Run Script

                                proc = runtime.exec("su");
                                osw = new OutputStreamWriter(proc.getOutputStream());
                                osw.write(command);
                                osw.flush();
                                osw.close();

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            Toast.makeText(HhActivity.this, "Rebooting to Bootloader", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.create().show();
                }
            }
        });

        // Power Off Button
        final Button poweroff = (Button) findViewById(R.id.poweroff);
        poweroff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == poweroff) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HhActivity.this);
                    builder.setMessage("Are you sure you want to power off?");
                    builder.setTitle("Confirmation Dialog");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // do something after confirm
                            Runtime runtime = Runtime.getRuntime();
                            Process proc = null;
                            OutputStreamWriter osw = null;

                            String command = "/system/bin/reboot -p";

                            try { // Run Script

                                proc = runtime.exec("su");
                                osw = new OutputStreamWriter(proc.getOutputStream());
                                osw.write(command);
                                osw.flush();
                                osw.close();

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            Toast.makeText(HhActivity.this, "Powering off now!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.create().show();
                }
            }
        });

        //  Needed for communication between watch and device.
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Log.d(TAG, "onConnected: " + connectionHint);
                    }
                    @Override
                    public void onConnectionSuspended(int cause) {
                        Log.d(TAG, "onConnectionSuspended: " + cause);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.d(TAG, "onConnectionFailed: " + result);
                    }
                })
                .addApi(Wearable.API)
                .build();

        googleApiClient.connect();
    }
}