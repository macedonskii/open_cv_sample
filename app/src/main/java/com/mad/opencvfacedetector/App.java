package com.mad.opencvfacedetector;

import android.app.Application;

import com.mad.opencvfacedetector.di.AppComponent;
import com.mad.opencvfacedetector.di.ContextModule;
import com.mad.opencvfacedetector.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}
