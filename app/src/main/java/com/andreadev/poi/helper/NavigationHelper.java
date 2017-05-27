package com.andreadev.poi.helper;

import android.app.Activity;
import android.content.Intent;

import com.andreadev.poi.R;
import com.andreadev.poi.utils.Constant;
import com.andreadev.poi.views.details.DetailsActivity;
import com.andreadev.poi.views.home.HomeActivity;

/**
 * Created by andrea on 25/05/2017.
 */

public class NavigationHelper {


    public static void navigateToHome(Activity activity){
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }

    public static void navigateToDetails(Activity activity, String id, String name){
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(Constant.INTENT_EXTRE_POI_ID, id);
        intent.putExtra(Constant.INTENT_EXTRE_POI_TITLE, name);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }
}
