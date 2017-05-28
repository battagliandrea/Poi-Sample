package com.andreadev.poi.views.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.andreadev.poi.R;
import com.andreadev.poi.base.BaseActivity;
import com.andreadev.poi.models.Poi;
import com.andreadev.poi.views.home.core.HomePresenter;
import com.andreadev.poi.views.home.core.IHomeView;
import com.andreadev.poi.views.home.fragments.ListPOIFragment;
import com.andreadev.poi.views.home.fragments.MapFragment;
import com.andreadev.poi.views.adapters.ViewPagerAdapter;

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


        if(getResources().getBoolean(R.bool.isTablet)){
            setupTabletFragment();
        } else{
            setupViewPager();
            setupNavigation();
        }

        presenter = new HomePresenter(this);
        presenter.getList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

        setupSearchView(menu);
        return true;
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
        adapter.addFragment(new ListPOIFragment(), getResources().getString(R.string.title_list));
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
                            ((ListPOIFragment)((ViewPagerAdapter)viewpager.getAdapter()).getItem(0)).onResumeFragment();
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

    private void setupTabletFragment(){
        FrameLayout frameOne = (FrameLayout)findViewById(R.id.list_container);
        FrameLayout frameTwo = (FrameLayout)findViewById(R.id.maps_container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListPOIFragment listFragment = new ListPOIFragment();
        fragmentTransaction.add(frameOne.getId(), listFragment , getResources().getString(R.string.title_list));

        MapFragment mapFragment = new MapFragment();
        fragmentTransaction.add(frameTwo.getId(), mapFragment , getResources().getString(R.string.title_map));
        fragmentTransaction.commit();
    }


    @Override
    public void getListSuccess(List<Poi> data) {
        try{
            if(getResources().getBoolean(R.bool.isTablet)){
                FragmentManager fragmentManager = getSupportFragmentManager();
                ((ListPOIFragment) fragmentManager.findFragmentByTag(getResources().getString(R.string.title_list))).setData(data);
                ((MapFragment) fragmentManager.findFragmentByTag(getResources().getString(R.string.title_map))).setData(data);
            } else{
                ((ListPOIFragment)((ViewPagerAdapter)viewpager.getAdapter()).getItem(0)).setData(data);
                ((MapFragment)((ViewPagerAdapter)viewpager.getAdapter()).getItem(1)).setData(data);
            }
        }catch (NullPointerException | ClassCastException e){
            e.printStackTrace();
        }
    }

    private void setupSearchView(Menu menu){
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(presenter!=null)
                    presenter.filterList(newText);
                return false;
            }
        });
    }
}
