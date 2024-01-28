package dev.roxs.altitudemeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.Manifest;
import android.location.LocationListener;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            // Permission is already granted, start location updates
            startLocationUpdates();
        }

    }

    private void startLocationUpdates() {
        TextView vAltitude = findViewById(R.id.altitudeView);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener locationListener = new MyLocationListener(vAltitude, findViewById(R.id.progressbar));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
    private static class MyLocationListener implements LocationListener {
        private final TextView altitudeTextView;
        private final ProgressBar vProgressbar;


        public MyLocationListener(TextView altitudeTextView, ProgressBar progressBar) {
            this.altitudeTextView = altitudeTextView;
            this.vProgressbar = progressBar;
        }

        public void onLocationChanged(Location location) {
            // Update your UI with the altitude
            double altitude = location.getAltitude();
            updateAltitudeInView(altitude);
        }

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        private void updateAltitudeInView(double altitude) {

            vProgressbar.setVisibility(View.INVISIBLE);
            altitudeTextView.setText("Altitude: " + String.format("%.3f", altitude) + " meters");
        }
    }
}