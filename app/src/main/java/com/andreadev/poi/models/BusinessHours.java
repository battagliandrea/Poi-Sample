package com.andreadev.poi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andrea on 26/05/2017.
 */

public class BusinessHours {

    @SerializedName("day")
    public String day;

    @SerializedName("schedules")
    public List<String> schedules;
}
