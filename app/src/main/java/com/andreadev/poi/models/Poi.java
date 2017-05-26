package com.andreadev.poi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andrea on 26/05/2017.
 */

public class Poi {
        @SerializedName("name")
        public String name;
        @SerializedName("address")
        public String address;
        @SerializedName("lat")
        public String lat;
        @SerializedName("lng")
        public String lng;
        @SerializedName("description")
        public String description;
        @SerializedName("imagePath")
        public String imagePath;
        @SerializedName("businessHours")
        public List<BusinessHours> businessHours;
}
