package com.andreadev.poi.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
