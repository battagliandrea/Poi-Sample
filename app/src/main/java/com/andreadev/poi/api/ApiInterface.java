package com.andreadev.poi.api;


import com.andreadev.poi.api.response.TestResponse;

import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by andrea on 09/07/16.
 */

public interface ApiInterface {

    @GET("exercise.json")
    Observable<TestResponse> testjson();
}