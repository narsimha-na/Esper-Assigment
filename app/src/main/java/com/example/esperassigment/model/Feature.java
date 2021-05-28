package com.example.esperassigment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feature {
    @SerializedName("featureId")
    @Expose
    private String featureId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public Feature() {
    }

    public Feature(String featureId, String name, List<Option> options) {
        this.featureId = featureId;
        this.name = name;
        this.options = options;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
