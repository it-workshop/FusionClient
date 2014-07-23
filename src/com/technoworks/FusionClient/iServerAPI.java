package com.technoworks.FusionClient;

import java.util.List;

/**
 * что-то вроде api
 * Created by lgor on 06.07.14.
 */
public interface iServerAPI {

    /**
     * примерное описание API
     * https://basecamp.com/2244849/projects/5728955/documents/6029060
     *
     * здесь будут всякие удобные для вызова из кода методы по отправке запросов серверу
     *
     * например,
     *
     * track();
     *
     * getLocation()
     *
     * и т.д
     *
     * пока всё очень тестовое и с заглушками
     */
    public iLocation getCopter();

    /**
     * @return success of sending
     */
    public boolean sendMyLocation(iLocation me);

    public boolean serverAvailible();

    public List<iLocation> loadLocations();

    public boolean requestCopter();

    public static class Singleton{
        private Singleton() {
        }

        private static iServerAPI APIrealization = null;

        public static iServerAPI get() {
            synchronized (APIrealization){
                if (APIrealization == null){
                    APIrealization = new ServerFake();
                }
                return APIrealization;
            }
        }
    }
}
