package com.andreadev.poi.views.details.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andreadev.poi.R;
import com.andreadev.poi.models.BusinessHours;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by andrea on 17/10/16.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {

    private List<BusinessHours> businesHours;

    public HorizontalAdapter(List<BusinessHours> businessHours) {
        this.businesHours = businessHours;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_details_row, parent, false);
        return new HorizontalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HorizontalViewHolder holder, int position) {
        BusinessHours item = businesHours.get(position);
        holder.dayOfWeekText.setText(item.day);


        if(item.schedules!=null){
            holder.closeLayout.setVisibility(View.GONE);
            holder.dayHoursText.setText(item.schedules.get(0));
            holder.afternoonHoursText.setText(item.schedules.get(1));
        }else{
            holder.closeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return businesHours.size();
    }


    public static class HorizontalViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.day_of_week_text_view)
        TextView dayOfWeekText;
        @InjectView(R.id.day_hours)
        TextView dayHoursText;
        @InjectView(R.id.afternoon_hours)
        TextView afternoonHoursText;
        @InjectView(R.id.close_layout)
        ConstraintLayout closeLayout;

        public HorizontalViewHolder(View vi) {
            super(vi);
            ButterKnife.inject(this, vi);
        }

    }
}