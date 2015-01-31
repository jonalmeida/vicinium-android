package io.evilolive.vicinium;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

public class LocationHandler implements LocationListener {
    private static final String LOCATION_HANDLER = LocationHandler.class.toString();
    private static LocationHandler ourInstance;

    private Context context;
    private Location location;
    private LocationManager locationManager;

    public LocationHandler(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 1.0f, this);
    }

    public static LocationHandler getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LocationHandler(context);
        }
        return ourInstance;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(LOCATION_HANDLER, "Location changed:\n lat: " + location.getLatitude() + "\n lon: " + location.getLongitude());
        setLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.v(LOCATION_HANDLER, "Status changed, but we do nothing...");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.v(LOCATION_HANDLER, "Provider enabled, but we do nothing...");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.v(LOCATION_HANDLER, "Provider disabled, but we do nothing...");
    }

    public void sampleLocation() {
        this.locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, Looper.getMainLooper());
    }

    public void resumeUpdates() {
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1.0f, this);
    }

    public void removeUpdates() {
        this.locationManager.removeUpdates(this);
    }

    public Location getLocation() {
        return location;
    }

    private void setLocation(Location location) {
        this.location = location;
    }
}
