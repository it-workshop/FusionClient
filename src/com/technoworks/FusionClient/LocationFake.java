package com.technoworks.FusionClient;

/**
 * фейковая неподвижная позиция
 * Created by lgor on 23.07.14.
 */
public class LocationFake implements iLocation {

    private final double x,y;
    private final String name;

    public LocationFake(String name, double lat, double lon){
        x = lon;
        y = lat;
        this.name = name;
    }

    public LocationFake(String name){
        x = y = 0;
        this.name = name;
    }

    @Override
    public double getLongtitude() {
        return x;
    }

    @Override
    public double getLatitude() {
        return y;
    }

    @Override
    public String getName() {
        return name;
    }
}
