package com.andreadev.poi.views.home.core;

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
        interactor.getPoi(this);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }
}
