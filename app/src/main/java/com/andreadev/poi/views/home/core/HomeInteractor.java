package com.andreadev.poi.views.home.core;

import android.util.Log;

import com.andreadev.poi.api.ApiClient;
import com.andreadev.poi.api.ApiInterface;
import com.andreadev.poi.api.response.TestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rx.Subscriber;
import rx.schedulers.Schedulers;

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
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<TestResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "TEST_JSON COMPLETED");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "TEST_JSON ERROR");
                        listener.onError();
                    }

                    @Override
                    public void onNext(TestResponse response) {
                        Log.d(TAG, "TEST_JSON SUCCESS");
                        if(response!=null){
                            Log.d(TAG, gson.toJson(response, TestResponse.class));
                            listener.onSuccess();
                        }
                    }
                });
    }
}
