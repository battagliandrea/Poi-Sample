package com.andreadev.poi.api;


import android.os.StrictMode;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrea on 09/07/16.
 */
public class ApiClient {


    public static final String BASE_URL = "http://synesthesia.it/sites/default/files/exercise/";


    private static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if (retrofit ==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
