package com.technoworks.FusionClient;

/**
 * Created by lgor on 21.07.14.
 */
public class GPSThread implements Runnable{

    private static GPSThread daemon = null;

    private Thread mThread;
    private volatile boolean running = true;

    private GPSThread(){
        mThread = new Thread(this);
        mThread.setDaemon(true);
        mThread.start();
    }

    @Override
    public void run() {
        while(running){
            //GPS update thread


        }
    }

    public void stop(){
        running = false;
    }

    public static GPSThread getThread(){
        synchronized (daemon){
            if (daemon == null || !daemon.running){
                daemon = new GPSThread();
            }
        }
        return daemon;
    }
}
