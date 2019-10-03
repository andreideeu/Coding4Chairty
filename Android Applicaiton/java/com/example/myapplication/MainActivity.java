package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = "MainActivity";
    private static final int cyclepass = 1515;
    private static final int runnerpass = 9080;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EnableRuntimePermission();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        criteria = new Criteria();

        Holder = locationManager.getBestProvider(criteria, false);

        context = getApplicationContext();

        CheckGpsStatus();

        if (GpsStatus == true) {
            if (Holder != null) {
                if (ActivityCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED

                        &&
                        ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
        } else {

            Toast.makeText(MainActivity.this, "Please Enable GPS First", Toast.LENGTH_LONG).show();
        }

        buttonStartThread = findViewById(R.id.buttonstart);
        buttonStopThread = findViewById(R.id.buttonstop);

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

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(MainActivity.this, "ACCESS_FINE_LOCATION permission allows us to Access GPS in app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this, "Permission Granted, Now your application can access GPS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this, "Permission Canceled, Now your application cannot access GPS.", Toast.LENGTH_LONG).show();

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

    public void sendMessagerun(View view) {

        EditText et = (EditText) findViewById(R.id.editText2);

        if(!et.getText().toString().matches("")){

            String password = et.getText().toString();
            int finalpass = Integer.parseInt(password);

            if (finalpass == runnerpass){

            Intent intent = new Intent(this, runnerclass.class);
            startActivity(intent);


    } else {
        Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();

    }
}
        else {
                Toast.makeText(MainActivity.this, "Enter a Password", Toast.LENGTH_LONG).show();

                }

    }

    public void sendMessagecycle(View view) {

        EditText et = (EditText) findViewById(R.id.editText2);

        if(!et.getText().toString().matches("")){

            String password = et.getText().toString();
            int finalpass = Integer.parseInt(password);

            if (finalpass == cyclepass) {

                Intent intent = new Intent(this, cyclistclass.class);
                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();

            }
        }
        else {
            Toast.makeText(MainActivity.this, "Enter a Password", Toast.LENGTH_LONG).show();

        }



    }
}


