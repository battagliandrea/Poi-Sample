package com.andreadev.poi.views.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andreadev.poi.R;
import com.andreadev.poi.base.BaseActivity;
import com.andreadev.poi.models.Poi;
import com.andreadev.poi.views.home.core.HomePresenter;
import com.andreadev.poi.views.home.core.IHomeView;
import com.andreadev.poi.views.home.fragments.ListFragment;
import com.andreadev.poi.views.home.fragments.MapFragment;
import com.andreadev.poi.views.home.adapters.ViewPagerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends BaseActivity implements IHomeView {

    @InjectView(R.id.navigation)
    BottomNavigationView navigation;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        this.setupToolbar(toolbar, false, getResources().getString(R.string.app_name));

        setupViewPager();
        setupNavigation();

        presenter = new HomePresenter(this);
        presenter.getList();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupNavigation() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_list:
                        viewpager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_map:
                        viewpager.setCurrentItem(1);
                        return true;
                }
                return false;
            }
        });
    }


    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(), getResources().getString(R.string.title_list));
        adapter.addFragment(new MapFragment(), getResources().getString(R.string.title_map));
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(adapter);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try{
                    switch (position) {
                        case 0:
                            ((ListFragment)((ViewPagerAdapter)viewpager.getAdapter()).getItem(0)).onResumeFragment();
                            navigation.setSelectedItemId(R.id.navigation_list);
                            break;
                        case 1:
                            ((MapFragment)((ViewPagerAdapter)viewpager.getAdapter()).getItem(1)).onResumeFragment();
                            navigation.setSelectedItemId(R.id.navigation_map);
                            break;
                    }
                }catch (NullPointerException | ClassCastException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void getListSuccess(List<Poi> data) {
        try{
            ((ListFragment)((ViewPagerAdapter)viewpager.getAdapter()).getItem(0)).setData(data);
            ((MapFragment)((ViewPagerAdapter)viewpager.getAdapter()).getItem(1)).setData(data);
        }catch (NullPointerException | ClassCastException e){
            e.printStackTrace();
        }
    }
}
