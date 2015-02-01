package io.evilolive.vicinium;

import android.location.Location;

public interface OnRoomChangeListener {
    public void updateFirebaseForRead(Location location);

    public void updateFirebaseForWrite(Location location);
}
