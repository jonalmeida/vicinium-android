package io.evilolive.vicinium;

import android.location.Location;

/**
 * Created by himanshu on 31/01/15.
 */
public interface MyListener {
    public void updateFirebaseForRead(Location location);

    public void updateFirebaseForWrite(Location location);
}
