package com.andreadev.poi.api;


import com.andreadev.poi.api.response.TestResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by andrea on 09/07/16.
 */

public interface ApiInterface {

    @GET("exercise.json")
    Observable<TestResponse> testjson();
}