package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class cyclistclass extends AppCompatActivity implements LocationListener {

    private static final String TAG = "cyclistclass";


    private Button buttonStartThread;
    private Button buttonStopThread;

    private Handler mainHandler = new Handler();

    private volatile boolean stopThread = false;

    int chooser = 100;

    public static final int RequestPermissionCode = 1;
    Context context;
    Intent intent1;
    Location location;
    LocationManager locationManager;

    boolean GpsStatus = false;
    Criteria criteria;
    String Holder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cyclistview);

        EnableRuntimePermission();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        criteria = new Criteria();

        Holder = locationManager.getBestProvider(criteria, false);

        context = getApplicationContext();

        CheckGpsStatus();

        if (GpsStatus == true) {
            if (Holder != null) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                location = locationManager.getLastKnownLocation(Holder);
                locationManager.requestLocationUpdates(Holder, 12000, 7, this);
            }
        } else {

            Toast.makeText(this, "Please Enable GPS First", Toast.LENGTH_LONG).show();

        }

        buttonStartThread = findViewById(R.id.buttonstart);
        buttonStopThread = findViewById(R.id.buttonstop);

    }

    public void startThread(View view) {
        stopThread = false;
        Log.i("PRESSED", "START");

        Toast.makeText(this, "STARTED", Toast.LENGTH_LONG).show();
        buttonStartThread.setBackgroundColor(Color.rgb(76, 175, 80));
        buttonStopThread.setBackgroundColor(Color.rgb(33, 150, 243));
        ExampleRunnable runnable = new ExampleRunnable(999999999);
        new Thread(runnable).start();
        /*
        ExampleThread thread = new ExampleThread(10);
        thread.start();
        */
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                //work
            }
        }).start();
        */
    }

    public void stopThread(View view) {
        Log.i("PRESSED", "STOP");
        Toast.makeText(this, "STOPPED", Toast.LENGTH_LONG).show();
        buttonStopThread.setBackgroundColor(Color.rgb(244, 67, 54));
        buttonStartThread.setBackgroundColor(Color.rgb(33, 150, 243));
        stopThread = true;
    }


    class ExampleThread extends Thread {
        int seconds;

        ExampleThread(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ExampleRunnable implements Runnable {
        int seconds;

        ExampleRunnable(int seconds) {
            this.seconds = seconds;
        }


        @Override
        public void run() {

            for (int i = 0; i < seconds; i++) {

                CheckGpsStatus();

                EditText et = (EditText) findViewById(R.id.editText);
                String name = et.getText().toString();

                APIConn_cycle apiConn = new APIConn_cycle();
                double currentLat = location.getLatitude();
                double currentLng = location.getLongitude();

                apiConn.setCurrentLat(String.valueOf(currentLat));
                apiConn.setCurrentLng(String.valueOf(currentLng));
                apiConn.setusername(name);

                apiConn.getme();

                try {


                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String threadCode = String.valueOf(apiConn.getCode());


                if ( threadCode.equals("0") == true) {

                    Log.i("Result", "Sending to Server");

                }



                if ( threadCode.equals("5") == true) {

                    Log.i("Result", "Some Error Sending");

                    buttonStopThread.setBackgroundColor(Color.rgb(244, 67, 54));
                    buttonStartThread.setBackgroundColor(Color.rgb(33, 150, 243));
                    stopThread = true;
                    return;
                }

                if (stopThread) {
                    return;
                }
                if (i == 5) {


                    /*
                    Handler threadHandler = new Handler(Looper.getMainLooper());
                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });
                    */
                    /*
                    buttonStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });
                    */
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        // textViewLongitude.setText("Longitude:" + location.getLongitude());
        // textViewLatitude.setText("Latitude:" + location.getLatitude());

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void CheckGpsStatus() {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(this, "ACCESS_FINE_LOCATION permission allows us to Access GPS in app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission Granted, Now your application can access GPS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this, "Permission Canceled, Now your application cannot access GPS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    public static String getCurrentTimeStamp() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}


