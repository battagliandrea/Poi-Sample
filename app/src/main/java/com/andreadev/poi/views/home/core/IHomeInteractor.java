package com.andreadev.poi.views.home.core;

/**
 * Created by andrea on 26/05/2017.
 */

public interface IHomeInteractor {

    interface OnGetPoiListener {
        void onError();
        void onSuccess();
    }

    void getPoi(OnGetPoiListener listener);
}
