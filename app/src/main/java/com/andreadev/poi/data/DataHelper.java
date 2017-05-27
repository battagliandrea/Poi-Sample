package com.andreadev.poi.data;



import com.andreadev.poi.models.Poi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andrea on 14/10/16.
 */

public class DataHelper {

    private String TAG = getClass().getSimpleName();

    static DataHelper singleIstance;//SINGLETON CREATION
    public static DataHelper getIstance(){
        if(singleIstance==null){
            singleIstance = new DataHelper();
        }
        return singleIstance;
    }

    private List<Poi> pointsOfInterest = new ArrayList<>();

    public List<Poi> getPointsOfInterest() {
        return pointsOfInterest;
    }
    public void setPointsOfInterest(List<Poi> pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }
}
