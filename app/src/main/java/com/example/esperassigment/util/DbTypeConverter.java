package com.example.esperassigment.util;

import androidx.room.TypeConverter;

import com.example.esperassigment.model.Feature;
import com.example.esperassigment.model.FeaturesResponse;
import com.example.esperassigment.model.Option;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DbTypeConverter {
    @TypeConverter
    public static List<Feature> featureStringToObj(String data){
        if(data == null){
            return null;
        }
        Type listType = new TypeToken<List<Feature>>(){}.getType();

        return new Gson().fromJson(data,listType);
    }
    @TypeConverter
    public static  String featureObjToString(List<Feature> data){
        return new Gson().toJson(data);
    }

}
