package com.example.esperassigment.db;

import android.app.Application;
import android.os.AsyncTask;

import com.example.esperassigment.model.FeaturesResponse;

import java.util.concurrent.ExecutionException;

public class FeaturesRepository {

    private final FeaturesDao featuresDao;

    public FeaturesRepository(Application application){
        FeaturesDatabase db = FeaturesDatabase.getInstance(application);
        featuresDao = db.FeatureDao();
    }
    /*
           Creating  methods for different tasks
        */
    public void insertFeatures(FeaturesResponse data){ new InsertFeaturesAsync(featuresDao).execute(data);}

    public void updateFeatures(FeaturesResponse data){new UpdateFeaturesAsync(featuresDao).execute(data);}

    public void deleteFeatures(FeaturesResponse data){ new DeleteFeaturesAsync(featuresDao).execute(data);}

    public FeaturesResponse getAllFeatures(){
        try {
            return  new GetAllFeaturesAsync(featuresDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
        Creating  Async tasks for different tasks
     */

    public static class InsertFeaturesAsync extends AsyncTask<FeaturesResponse,Void,Void> {
        private FeaturesDao featuresDao;
        public InsertFeaturesAsync(FeaturesDao featuresDao) { this.featuresDao = featuresDao;}
        @Override
        protected Void doInBackground(FeaturesResponse... data) {
            featuresDao.insert(data[0]);
            return null;
        }
    }

    public static class UpdateFeaturesAsync extends AsyncTask<FeaturesResponse,Void,Void>{
        private FeaturesDao featuresDao;

        public UpdateFeaturesAsync(FeaturesDao featuresDao) {
            this.featuresDao = featuresDao;
        }

        @Override
        protected Void doInBackground(FeaturesResponse... featuresResponses) {
            featuresDao.update(featuresResponses[0]);
            return null;
        }
    }

    private static class DeleteFeaturesAsync extends AsyncTask<FeaturesResponse,Void,Void>{
        private FeaturesDao featuresDao;
        public DeleteFeaturesAsync(FeaturesDao featuresDao) {
            this.featuresDao = featuresDao;
        }

        @Override
        protected Void doInBackground(FeaturesResponse... featuresResponses) {
            featuresDao.delete(featuresResponses[0]);
            return null;
        }
    }

    private static class GetAllFeaturesAsync extends AsyncTask<Void,Void,FeaturesResponse>{
        private FeaturesDao featuresDao;
        public GetAllFeaturesAsync(FeaturesDao featuresDao) {
            this.featuresDao = featuresDao;
        }

        @Override
        protected FeaturesResponse doInBackground(Void... voids) {
            return featuresDao.getFeatures();
        }
    }
}
