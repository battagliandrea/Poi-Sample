package com.andreadev.poi.views.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.andreadev.poi.R;
import com.andreadev.poi.base.BaseActivity;
import com.andreadev.poi.models.Poi;
import com.andreadev.poi.utils.Constant;
import com.andreadev.poi.views.adapters.HorizontalAdapter;
import com.andreadev.poi.views.details.core.DetailsPresenter;
import com.andreadev.poi.views.details.core.IDetailsView;
import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailsActivity extends BaseActivity implements IDetailsView {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.details_image_view)
    ImageView imageView;
    @InjectView(R.id.details_name_text_view)
    TextView nameTextView;
    @InjectView(R.id.details_description_text_view)
    TextView descriptionTextView;
    @InjectView(R.id.details_address_text_view)
    TextView addressTextView;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerViewHorizontal;

    private DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);

        this.setupToolbar(toolbar, true, getIntent().getStringExtra(Constant.INTENT_EXTRE_POI_TITLE));

        presenter = new DetailsPresenter(this);
        presenter.getPoiDetails(getIntent().getStringExtra(Constant.INTENT_EXTRE_POI_ID));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    @Override
    public void onSuccess(Poi value) {
        try {
            nameTextView.setText(value.name);
            addressTextView.setText(value.address);
            descriptionTextView.setText(value.description);

            Glide.with(this).load(value.imagePath).error(R.drawable.placeholder_details).centerCrop().into(imageView);

            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(value.businessHours);
            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewHorizontal.setLayoutManager(horizontalLayoutManagaer);
            recyclerViewHorizontal.setAdapter(horizontalAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
            //TODO: alert di errore
        }
    }

    @Override
    public void onError() {
        //TODO alert di errore
    }


}
