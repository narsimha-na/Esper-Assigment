package com.example.esperassigment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.esperassigment.util.DbTypeConverter;
import com.example.esperassigment.util.ListExclusionTypeConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "features")
public class FeaturesResponse {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("features")
    @Expose
    @TypeConverters(DbTypeConverter.class)
    private List<Feature> featuresList = null;

    @SerializedName("exclusions")
    @Expose
    @TypeConverters(ListExclusionTypeConverter.class)
    private List<List<Exclusion>> exclusionsList = null;

    public FeaturesResponse() {
    }

    public FeaturesResponse(int id, List<Feature> featuresList, List<List<Exclusion>> exclusionsList) {
        this.id = id;
        this.featuresList = featuresList;
        this.exclusionsList = exclusionsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Feature> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<Feature> featuresList) {
        this.featuresList = featuresList;
    }

    public List<List<Exclusion>> getExclusionsList() {
        return exclusionsList;
    }

    public void setExclusionsList(List<List<Exclusion>> exclusionsList) {
        this.exclusionsList = exclusionsList;
    }

    @Override
    public String toString() {
        return "FeaturesResponse{" +
                "id=" + id +
                ", featuresList=" + featuresList +
                ", exclusionsList=" + exclusionsList +
                '}';
    }
}
