package com.andreadev.poi.views.details.core;

import com.andreadev.poi.models.Poi;

/**
 * Created by andrea on 27/05/2017.
 */

public interface IDetailsView {

    void onSuccess(Poi value);
    void onError();
}
