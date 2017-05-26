package com.andreadev.poi.widgets;

import android.support.annotation.Nullable;

import com.andreadev.poi.models.Poi;

import java.util.List;

/**
 * Created by Andrea on 28/09/16.
 */

public interface FragmentLifecycle {

    void onPauseFragment();
    void onResumeFragment();
    void setData(List<Poi> data);
}
