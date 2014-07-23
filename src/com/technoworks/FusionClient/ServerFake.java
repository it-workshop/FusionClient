package com.technoworks.FusionClient;

import java.util.ArrayList;
import java.util.List;

/**
 * заглушка вместо сервера
 * Created by lgor on 23.07.14.
 */
public class ServerFake implements iServerAPI {

    @Override
    public iLocation getCopter() {
        return iLocation.emptyLocation;
    }

    @Override
    public boolean sendMyLocation(iLocation me) {
        return true;
    }

    @Override
    public boolean serverAvailible() {
        return true;
    }

    @Override
    public List<iLocation> loadLocations() {
        ArrayList<iLocation> locations = new ArrayList<iLocation>();
        locations.add(new LocationFake("Alice"));
        locations.add(new LocationFake("Bob"));
        return locations;
    }

    @Override
    public boolean requestCopter() {
        return true;
    }
}
