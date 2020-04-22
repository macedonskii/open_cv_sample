package com.mad.opencvfacedetector.di;

import android.content.Context;

import dagger.Component;

@Component(modules = {ContextModule.class, ModelModule.class})
public interface AppComponent {
}
