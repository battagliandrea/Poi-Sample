package com.andreadev.poi.views.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andreadev.poi.R;
import com.andreadev.poi.helper.OnItemSelectedListener;
import com.andreadev.poi.models.Poi;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by andrea on 26/05/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {


    private Context context;
    private List<Poi> pointsOfInterest;

    private OnItemSelectedListener<Poi> onItemSelectedListener;

    public ListAdapter(Context context) {
        this.context = context;
        this.pointsOfInterest = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_poi_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Poi poi = pointsOfInterest.get(position);

        holder.itemName.setText(poi.name);
        holder.itemAddress.setText(poi.address);

        Glide.with(context).load(poi.imagePath).centerCrop().error(R.mipmap.ic_launcher).into(holder.itemAvatar);
    }

    @Override
    public int getItemCount() {
        return pointsOfInterest.size();
    }

    public void setPoi(List<Poi> data) {
        pointsOfInterest.clear();
        pointsOfInterest.addAll(data);
        this.notifyDataSetChanged();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener<Poi> onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.item_name)
        TextView itemName;
        @InjectView(R.id.item_avatar)
        CircleImageView itemAvatar;
        @InjectView(R.id.item_address)
        TextView itemAddress;

        public ViewHolder(View vi) {
            super(vi);
            ButterKnife.inject(this, vi);

            vi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemSelectedListener != null)
                        onItemSelectedListener.onItemSelected(pointsOfInterest.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

    }
}
