package com.andreadev.poi.api;

import com.andreadev.poi.models.Poi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.UUID;

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

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Poi.class, new JsonDeserializer<Poi>() {

                        @Override
                        public Poi deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            Gson g = new Gson();
                            Poi p = g.fromJson(json, Poi.class);
                            p.id = UUID.randomUUID().toString();

                            return p;
                        }
                    })
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
