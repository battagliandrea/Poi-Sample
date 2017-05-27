package com.andreadev.poi.views.home.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.andreadev.poi.R;
import com.andreadev.poi.helper.NavigationHelper;
import com.andreadev.poi.helper.OnItemSelectedListener;
import com.andreadev.poi.models.Poi;
import com.andreadev.poi.views.home.HomeActivity;
import com.andreadev.poi.views.home.adapters.ListAdapter;
import com.andreadev.poi.helper.HomeFragmentCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ListFragment extends Fragment implements HomeFragmentCallback {


    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view);

        setupRecyclerView();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_menu, menu);

        setupSearchView(menu);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                //TODO: Not implemented here
                return false;
            default:
                break;
        }
        return false;
    }*/

    private void setupRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemSelectedListener(new OnItemSelectedListener<Poi>() {
            @Override
            public void onItemSelected(Poi item, int position) {
                NavigationHelper.navigateToDetails(getActivity(), item.id, item.name);
            }
        });
    }

    private void setupSearchView(Menu menu){
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((HomeActivity)getActivity()).filterList(newText);
                return false;
            }
        });
    }

    @Override
    public void onResumeFragment() {

    }

    @Override
    public void setData(List<Poi> data) throws NullPointerException{
        adapter.setPoi(data);
    }
}
