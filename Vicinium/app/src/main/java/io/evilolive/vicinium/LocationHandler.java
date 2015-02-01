package io.evilolive.vicinium;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

public class LocationHandler implements LocationListener {
    private static final String TAG = LocationHandler.class.toString();
    private static LocationHandler ourInstance = null;
    public static OnRoomChangeListener listener = null;

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
        Activity activity = (Activity) context;
        if (listener == null && activity.getLocalClassName().equals("MessageList")) {
            listener = (OnRoomChangeListener) activity;
        }
        if (ourInstance == null) {
            ourInstance = new LocationHandler(context);
        }
        return ourInstance;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(TAG, "Location changed:\n lat: " + location.getLatitude() + "\n lon: " + location.getLongitude());
        RoomCoordinates oldCoordinates = new RoomCoordinates(this.location);
        RoomCoordinates newCoordinates = new RoomCoordinates(location);

        boolean diff = ((newCoordinates.latitude - oldCoordinates.latitude) == 0) && ( (newCoordinates.longitude - oldCoordinates.longitude) == 0);

        setLocation(location);
        if (listener != null && (!diff)) {
            listener.updateFirebaseForRead(location);
            listener.updateFirebaseForWrite(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.v(TAG, "Status changed, but we do nothing...");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.v(TAG, "Provider enabled, but we do nothing...");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.v(TAG, "Provider disabled, but we do nothing...");
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
