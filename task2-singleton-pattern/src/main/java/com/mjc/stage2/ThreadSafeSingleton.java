package com.mjc.stage2;

public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton instance;
    private static final Object mutex = new Object();

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton getInstance() {
        ThreadSafeSingleton localInstance = instance;
        if(localInstance == null) {
            synchronized (mutex) {
                instance = localInstance = new ThreadSafeSingleton();
            }
        }
        return localInstance;
    }
}
