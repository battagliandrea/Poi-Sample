package com.andreadev.poi.api.response;

import com.andreadev.poi.models.Poi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andrea on 26/05/2017.
 */

public class TestResponse {

    @SerializedName("res")
    public String res;
    @SerializedName("data")
    public List<Poi> data;
}
