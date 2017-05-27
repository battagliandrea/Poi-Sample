package com.andreadev.poi.views.home.core;

import android.util.Log;

import com.andreadev.poi.data.DataHelper;
import com.andreadev.poi.models.Poi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by andrea on 26/05/2017.
 */

public class HomePresenter implements IHomePresenter, IHomeInteractor.OnGetPoiListener{

    private final String TAG = getClass().getSimpleName();

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
    public void filterList(final String query) {
        final List<Poi> filterList = new ArrayList<>();
        Observable.just(DataHelper.getIstance().getPointsOfInterest())
                .flatMap(new Function<List<Poi>, ObservableSource<Poi>>() {
                    @Override
                    public ObservableSource<Poi> apply(List<Poi> list) throws Exception {
                        return Observable.fromIterable(list);
                    }
                })
                .filter(new Predicate<Poi>(){
                    @Override
                    public boolean test(Poi o) throws Exception {
                        String lowerCase = query.toLowerCase(Locale.getDefault());
                        if (lowerCase.length() == 0) {
                            return true;
                        }else{
                            if (o.name.toLowerCase(Locale.getDefault()).contains(lowerCase)){
                                return true;
                            }
                            return false;
                        }
                    }
                })
                .subscribeWith(new Observer<Poi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Poi poi) {
                        Log.d(TAG, "FILTER OK: " + poi.name);
                        filterList.add(poi);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "FILTER ERROR");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "FILTER COMPLETE");
                        view.filterListSuccess(filterList);
                    }
                });
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
