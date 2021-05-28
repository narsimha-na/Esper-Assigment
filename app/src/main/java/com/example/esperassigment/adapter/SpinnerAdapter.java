package com.example.esperassigment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.esperassigment.R;
import com.example.esperassigment.model.Option;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<Option> {

    public SpinnerAdapter(Context context, ArrayList<Option> optionsList) {
        super(context, 0, optionsList);
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
            imageViewFlag.setImageResource(R.drawable.ic_launcher_background);
            textViewName.setText(currentItem.getName());
        }

        return itemView;
    }
}