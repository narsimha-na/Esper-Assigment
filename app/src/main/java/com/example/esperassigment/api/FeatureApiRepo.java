package com.example.esperassigment.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.esperassigment.model.Exclusion;
import com.example.esperassigment.model.Feature;
import com.example.esperassigment.model.FeaturesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeatureApiRepo {

    private final MutableLiveData<FeaturesResponse> featuresResp;
    public static final String TAG = "FeatureApiRepo";

    public FeatureApiRepo() {
        featuresResp = new MutableLiveData<>();
    }

    //Getting the data from API Call
    public MutableLiveData<FeaturesResponse> getFeaturesList(Context context) {
        RetrofitInstance
                .getInstance()
                .getRepo()
                .getResponse()
                .enqueue(new Callback<FeaturesResponse>() {
                    @Override
                    public void onResponse(Call<FeaturesResponse> call, Response<FeaturesResponse> response) {
                        if (response.body() != null) {
                            featuresResp.setValue(response.body());
                        } else {
                            Toast.makeText(context, "Error please try again ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FeaturesResponse> call, Throwable t) {

                    }
                });

        return featuresResp;
    }

}
