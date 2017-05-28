package com.andreadev.poi.base;

import android.app.Application;
import android.content.Context;

import com.karumi.dexter.Dexter;

/**
 * Created by andrea on 26/05/2017.
 */

public class PoiApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Dexter.initialize(this);
    }

    public static Context getContext(){
        return context;
    }
}
