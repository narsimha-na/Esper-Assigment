package com.example.esperassigment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exclusion {
    @SerializedName("feature_id")
    @Expose
    private String featureId;
    @SerializedName("options_id")
    @Expose
    private String optionsId;

    public Exclusion() {
    }

    public Exclusion(String featureId, String optionsId) {
        this.featureId = featureId;
        this.optionsId = optionsId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId;
    }

    @Override
    public String toString() {
        return "Exclusion{" +
                "featureId='" + featureId + '\'' +
                ", optionsId='" + optionsId + '\'' +
                '}';
    }
}
