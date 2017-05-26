package com.andreadev.poi.views.home.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andreadev.poi.R;
import com.andreadev.poi.models.Poi;
import com.andreadev.poi.views.home.adapters.ListAdapter;
import com.andreadev.poi.widgets.FragmentLifecycle;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ListFragment extends Fragment implements FragmentLifecycle {


    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ListAdapter adapter;

    public ListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }

    @Override
    public void setData(List<Poi> data) throws NullPointerException{
        adapter.setPoi(data);
    }
}
