package com.technoworks.FusionClient;

/**
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
     */

    public static class Singleton{
        private Singleton() {
        }

        private static iServerAPI APIrealization = null;

        public static iServerAPI get() {
            synchronized (APIrealization){
                if (APIrealization == null){
                    //APIrealization = new iServerAPI();
                }
                return APIrealization;
            }
        }
    }
}
