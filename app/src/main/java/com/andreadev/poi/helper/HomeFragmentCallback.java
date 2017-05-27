package com.andreadev.poi.helper;

import android.support.annotation.Nullable;

import com.andreadev.poi.models.Poi;

import java.util.List;

/**
 * Created by Andrea on 28/09/16.
 */

public interface HomeFragmentCallback {

    void onResumeFragment();
    void setData(List<Poi> data);
}
