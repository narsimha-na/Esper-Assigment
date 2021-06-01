package com.example.esperassigment.api;

import com.example.esperassigment.model.FeaturesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    /*
        API Call used for getting the data from the URL
     */
    @GET(".")
    Call<FeaturesResponse> getResponse();

}
