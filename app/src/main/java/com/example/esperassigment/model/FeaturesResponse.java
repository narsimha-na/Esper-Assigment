package com.example.esperassigment.model;

import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "features")
public class FeaturesResponse {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("features")
    @Expose
    private LiveData<List<FeaturesResponse>> featuresList = null;
    @SerializedName("exclusions")
    @Expose
    private LiveData<List<List<Exclusion>>> exclusionsList = null;

    public FeaturesResponse() {
    }

    public FeaturesResponse(LiveData<List<FeaturesResponse>> featuresList, LiveData<List<List<Exclusion>>> exclusions) {
        this.featuresList = featuresList;
        this.exclusionsList = exclusions;
    }

    public LiveData<List<FeaturesResponse>> getFeatures() {
        return featuresList;
    }

    public void setFeatures(LiveData<List<FeaturesResponse>> features) {
        this.featuresList = features;
    }

    public LiveData<List<List<Exclusion>>> getExclusions() {
        return exclusionsList;
    }

    public void setExclusions(LiveData<List<List<Exclusion>>> exclusions) {
        this.exclusionsList = exclusions;
    }
}
