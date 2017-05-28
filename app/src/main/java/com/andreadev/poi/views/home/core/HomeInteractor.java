package com.andreadev.poi.views.home.core;

import android.util.Log;

import com.andreadev.poi.api.ApiClient;
import com.andreadev.poi.api.ApiInterface;
import com.andreadev.poi.api.response.TestResponse;
import com.andreadev.poi.base.PoiApp;
import com.andreadev.poi.data.DataHelper;
import com.andreadev.poi.helper.NetworkHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrea on 26/05/2017.
 */

public class HomeInteractor implements IHomeInteractor{

    private final String TAG = getClass().getSimpleName();
    private Gson gson;

    public HomeInteractor() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void getPoi(final OnGetPoiListener listener) {
        ApiInterface apiService = ApiClient.getApiClient().create(ApiInterface.class);

        apiService.testjson()
                .subscribeOn(Schedulers.newThread()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestResponse response) {
                        Log.d(TAG, "TEST_JSON SUCCESS");
                        if(response!=null && response.data!=null){
                            Log.d(TAG, gson.toJson(response, TestResponse.class));
                            DataHelper.getIstance().setPointsOfInterest(response.data);
                            listener.onSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "TEST_JSON ERROR");
                        listener.onError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "TEST_JSON COMPLETED");
                    }
                });
    }
}
