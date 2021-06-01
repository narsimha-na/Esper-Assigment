package com.example.esperassigment.util;

import androidx.room.TypeConverter;

import com.example.esperassigment.model.Exclusion;
import com.example.esperassigment.model.Feature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListExclusionTypeConverter {

    @TypeConverter
    public static List<List<Exclusion>> exclusionStringToObj(String data){
        if(data == null){
            return null;
        }
        Type listType = new TypeToken<List<List<Exclusion>>>(){}.getType();

        return new Gson().fromJson(data,listType);
    }
    @TypeConverter
    public static  String featureObjToString(List<List<Exclusion>> data){
        return new Gson().toJson(data);
    }

}