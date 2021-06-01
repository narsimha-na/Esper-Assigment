package com.example.esperassigment.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.esperassigment.model.Feature;
import com.example.esperassigment.model.FeaturesResponse;
import com.example.esperassigment.util.DbTypeConverter;
import com.example.esperassigment.util.ListExclusionTypeConverter;

import org.jetbrains.annotations.NotNull;

import static com.example.esperassigment.util.Constants.DB_NAME;

@Database(entities = {FeaturesResponse.class},version = 1)
@TypeConverters({DbTypeConverter.class, ListExclusionTypeConverter.class})
public abstract class FeaturesDatabase extends RoomDatabase {

    private static FeaturesDatabase instance;

    public abstract FeaturesDao FeatureDao();

    public static synchronized FeaturesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FeaturesDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        PopulateDbAsyncTask(FeaturesDatabase instance){FeaturesDao featuresDao = instance.FeatureDao();}

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
