package com.andreadev.poi.views.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.andreadev.poi.R;
import com.andreadev.poi.helper.NavigationHelper;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NavigationHelper.navigateToHome(SplashActivity.this);
                finish();
            }
        }, SPLASH_DURATION);
    }
}
