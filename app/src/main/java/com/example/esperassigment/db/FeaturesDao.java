package com.example.esperassigment.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.esperassigment.model.FeaturesResponse;

import static com.example.esperassigment.util.Constants.DB_NAME;

@Dao
public interface FeaturesDao {
    //Inserting of data into db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FeaturesResponse data);
    //Updating of data into db
    @Update
    void update(FeaturesResponse data);
    //Deleting of the data from db
    @Delete
    void delete(FeaturesResponse data);

    @Query("SELECT * FROM  features LIMIT 1")
    FeaturesResponse getFeatures();

}
