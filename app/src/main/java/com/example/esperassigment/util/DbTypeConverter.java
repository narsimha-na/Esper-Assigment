package com.example.esperassigment.util;

import androidx.room.TypeConverter;

import com.example.esperassigment.model.FeaturesResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DbTypeConverter {
    @TypeConverter
    public static FeaturesResponse featureStringToObj(String data){
        if(data == null){
            return null;
        }
        Type listType = new TypeToken<FeaturesResponse>(){}.getType();

        return new Gson().fromJson(data,listType);
    }
    @TypeConverter
    public static  String featureObjToString(FeaturesResponse data){
        return new Gson().toJson(data);
    }
}
