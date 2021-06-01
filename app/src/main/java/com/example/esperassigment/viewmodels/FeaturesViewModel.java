package com.example.esperassigment.viewmodels;


import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.esperassigment.api.Api;
import com.example.esperassigment.api.FeatureApiRepo;
import com.example.esperassigment.api.RetrofitInstance;
import com.example.esperassigment.db.FeaturesRepository;
import com.example.esperassigment.model.Exclusion;
import com.example.esperassigment.model.Feature;
import com.example.esperassigment.model.FeaturesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturesViewModel extends AndroidViewModel {

    private FeaturesRepository featuresRepository;
    private MutableLiveData<List<Feature>> featuresList;
    private MutableLiveData<List<List<Exclusion>>> exclusionList;
    private Api apiRepo;
    private MutableLiveData<FeaturesResponse> featuresResponse;
    public static final String TAG = "Viewmodel";

    public FeaturesViewModel(@NonNull Application application) {
        super(application);
    }

    /*
        Below are the operations performed using in memory database
     */
    public void insert(FeaturesResponse model) {
        featuresRepository.insertFeatures(model);
    }

    public void update(FeaturesResponse model) {
        featuresRepository.updateFeatures(model);
    }

    public void delete(FeaturesResponse model) {
        featuresRepository.deleteFeatures(model);
    }

    public LiveData<List<Feature>> getFeatureList() {
        FeaturesResponse response = featuresRepository.getAllFeatures();
        featuresList.postValue(response.getFeaturesList());
        return featuresList;
    }

    public LiveData<List<List<Exclusion>>> getExclusionList() {
        FeaturesResponse response = featuresRepository.getAllFeatures();
        exclusionList.postValue(response.getExclusionsList());
        return exclusionList;
    }

    // API Call

    public MutableLiveData<FeaturesResponse> getFeaturesResponse(Context context){
        FeatureApiRepo apiRepo = new FeatureApiRepo();
        return apiRepo.getFeaturesList(context);
    }


}
