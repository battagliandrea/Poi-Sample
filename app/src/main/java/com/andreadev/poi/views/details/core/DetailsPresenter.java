package com.andreadev.poi.views.details.core;

import android.util.Log;

import com.andreadev.poi.data.DataHelper;
import com.andreadev.poi.models.Poi;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by andrea on 27/05/2017.
 */

public class DetailsPresenter implements IDetailsPresenter {

    private final String TAG = getClass().getSimpleName();

    private IDetailsView view;

    public DetailsPresenter(IDetailsView view) {
        this.view = view;
    }

    @Override
    public void getPoiDetails(final String id) {
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
                       return (o).id.equals(id);
                   }
               })
               .subscribe(new Observer<Poi>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(Poi poi) {
                       Log.d(TAG, "DETAILS OK: " + poi.name);
                       view.onSuccess(poi);
                   }

                   @Override
                   public void onError(Throwable e) {
                        Log.d(TAG, "DETAILS ERROR");
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
}
