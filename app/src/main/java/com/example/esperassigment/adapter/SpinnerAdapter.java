package com.example.esperassigment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.esperassigment.R;
import com.example.esperassigment.model.Exclusion;
import com.example.esperassigment.model.Feature;
import com.example.esperassigment.model.Option;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Option> {

    private List<Option> featureList;
    private List<List<Exclusion>> exclusionList;
    public static final String TAG = "Spiner Adappter";
    private Context context;

    public SpinnerAdapter(@NonNull Context context, List<Option> featureList) {
        super(context, 0,featureList);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View itemView, ViewGroup parent) {
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_spinner, parent, false
            );
        }

        ImageView imageViewFlag = itemView.findViewById(R.id.image_view_flag);
        TextView textViewName = itemView.findViewById(R.id.text_view_name);

        Option currentItem = getItem(position);

        if (currentItem != null) {
            Glide
                    .with(context)
                    .load(currentItem.getIcon())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageViewFlag);
            textViewName.setText(currentItem.getName());
        }else{
            Log.d(TAG, "Adapter ");
        }

        return itemView;
    }
}