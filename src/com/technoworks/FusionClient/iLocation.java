package com.technoworks.FusionClient;

/**
 * То, к чему можно лететь
 * Created by lgor on 23.07.14.
 */
public interface iLocation {

    double getLongtitude();

    double getLatitude();

    String getName();


    public static final iLocation emptyLocation = new iLocation() {
        @Override
        public double getLongtitude() {
            return 0;
        }

        @Override
        public double getLatitude() {
            return 0;
        }

        public String getName(){
            return "empty Location";
        }
    };
}
