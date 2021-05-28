package com.example.esperassigment.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.esperassigment.db.FeaturesRepository;
import com.example.esperassigment.model.Exclusion;
import com.example.esperassigment.model.Feature;
import com.example.esperassigment.model.FeaturesResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FeaturesViewModel extends AndroidViewModel {
    
    private FeaturesRepository featuresRepository;
        private LiveData<List<Feature>> featuresList;
    private LiveData<List<List<Exclusion>>> exclusionList;
    
    public FeaturesViewModel(@NonNull @NotNull Application application) {
        super(application);
        featuresRepository =  new FeaturesRepository(application);
    }

    public void insert(FeaturesResponse model) {
        featuresRepository.insertFeatures(model);
    }

    public void update(FeaturesResponse model) {
        featuresRepository.updateFeatures(model);
    }

    public void delete(FeaturesResponse model) {
        featuresRepository.deleteFeatures(model);
    }

    public LiveData<List<FeaturesResponse>> getFeatureList() {
        FeaturesResponse response =   featuresRepository.getAllFeatures();
        return response.getFeatures();
    }
    public LiveData<List<List<Exclusion>>> getExclusionList() {
        FeaturesResponse response =   featuresRepository.getAllFeatures();
        return response.getExclusions();
    }
}
