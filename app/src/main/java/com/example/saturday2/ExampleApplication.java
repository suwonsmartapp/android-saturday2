package com.example.saturday2;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by junsuk on 2016. 12. 29..
 */

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
