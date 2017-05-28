package com.andreadev.poi.views.home.core;

import com.andreadev.poi.base.BaseView;
import com.andreadev.poi.models.Poi;

import java.util.List;

/**
 * Created by andrea on 26/05/2017.
 */

public interface IHomeView extends BaseView{

    void getListSuccess(List<Poi> data);
}
