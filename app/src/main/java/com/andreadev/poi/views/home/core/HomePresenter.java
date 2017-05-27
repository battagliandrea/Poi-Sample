package com.andreadev.poi.views.home.core;

import com.andreadev.poi.data.DataHelper;

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
    public void onSuccess() {
        view.getListSuccess(DataHelper.getIstance().getPointsOfInterest());
        view.hideProgress();
    }
}
