package com.andreadev.poi.views.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andreadev.poi.R;
import com.andreadev.poi.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends BaseActivity {

    @InjectView(R.id.navigation)
    BottomNavigationView navigation;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        this.setupToolbar(toolbar, false, getResources().getString(R.string.app_name));

    }

    private void setupNavigation() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //mTextMessage.setText(R.string.title_poi);
                        return true;
                    case R.id.navigation_dashboard:
                        //mTextMessage.setText(R.string.title_map);
                        return true;
                }
                return false;
            }
        });
    }

}
