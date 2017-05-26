package com.andreadev.poi.base;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by andrea on 26/05/2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseView{


    public void setupToolbar(Toolbar toolbar, boolean displayHome, @Nullable String title){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHome);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(title!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(title);
        }else{
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
