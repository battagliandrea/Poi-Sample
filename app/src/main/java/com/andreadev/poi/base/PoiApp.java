package com.andreadev.poi.base;

import android.app.Application;

import com.karumi.dexter.Dexter;

/**
 * Created by andrea on 26/05/2017.
 */

public class PoiApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Dexter.initialize(this);
    }
}
