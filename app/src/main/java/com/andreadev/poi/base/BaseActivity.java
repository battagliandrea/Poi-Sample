package com.andreadev.poi.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.andreadev.poi.R;

/**
 * Created by andrea on 26/05/2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseView {

    private static Dialog dialog;

    public void setupToolbar(Toolbar toolbar, boolean displayHome, @Nullable String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHome);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (title != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(title);
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void setupCollapsingToolbar(CollapsingToolbarLayout collapsingToolbarLayout, @Nullable String title) {
        if (title != null) {
            collapsingToolbarLayout.setTitle(title);
            collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, (android.R.color.transparent)));
        }
    }

    @Override
    public void showProgress() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_view);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }
}
