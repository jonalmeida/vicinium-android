package io.evilolive.vicinium;

import java.util.TimerTask;

class LocationUpdater extends TimerTask {

    private LocationHandler locationHandler;

    LocationUpdater(LocationHandler locationHandler) {
        this.locationHandler = locationHandler;
    }

    @Override
    public void run() {
        this.locationHandler.sampleLocation();
    }

}