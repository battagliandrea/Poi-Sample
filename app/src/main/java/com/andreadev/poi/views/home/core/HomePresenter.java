package com.andreadev.poi.views.home.core;

import android.os.Handler;

import com.andreadev.poi.models.Poi;

import java.util.List;

/**
 * Created by andrea on 26/05/2017.
 */

public class HomePresenter implements IHomePresenter, IHomeInteractor.OnGetPoiListener{

    private IHomeView view;
    private HomeInteractor interactor;

    public HomePresenter(IHomeView view) {
        this.view = view;
        interactor = new HomeInteractor();
    }

    @Override
    public void getList() {
        view.showProgress();
        interactor.getPoi(HomePresenter.this);
    }

    @Override
    public void onError() {
        view.hideProgress();
    }

    @Override
    public void onSuccess(List<Poi> data) {
        view.getListSuccess(data);
        view.hideProgress();
    }
}
