package com.example.esperassigment.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.esperassigment.util.Constants.BASE_URL;

public class RetrofitInstance {

    private static RetrofitInstance retrofitInstance;
    private final Retrofit mRetorfit;

    public RetrofitInstance() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .build();
        /*
            Initializing the Retrofit
         */
        mRetorfit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /*
        Checking weather the retrofit is initialized or not
     */
    public static synchronized RetrofitInstance getInstance(){
        if(retrofitInstance == null){
            retrofitInstance = new RetrofitInstance();
        }
        return retrofitInstance;
    }

    public Api getRepo(){
        return mRetorfit.create(Api.class);
    }
}
