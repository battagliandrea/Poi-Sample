package com.andreadev.poi.views.home.core;

import com.andreadev.poi.models.Poi;

import java.util.List;

/**
 * Created by andrea on 26/05/2017.
 */

public interface IHomeInteractor {

    interface OnGetPoiListener {
        void onError();
        void onSuccess(List<Poi> data);
    }

    void getPoi(OnGetPoiListener listener);
}
