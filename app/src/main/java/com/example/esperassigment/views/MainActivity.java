package com.example.esperassigment.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esperassigment.R;
import com.example.esperassigment.adapter.SpinnerAdapter;
import com.example.esperassigment.db.FeaturesRepository;
import com.example.esperassigment.model.Exclusion;
import com.example.esperassigment.model.Feature;
import com.example.esperassigment.model.FeaturesResponse;
import com.example.esperassigment.model.Option;
import com.example.esperassigment.viewmodels.FeaturesViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.esperassigment.util.Constants.OPTION_DATA;
import static com.example.esperassigment.util.DbTypeConverter.featureObjToString;
import static com.example.esperassigment.util.InternetConnection.hasInternetConnection;

public class MainActivity extends AppCompatActivity {

    private Spinner mobilesSpinner, memorySpinner, otherSpinner;
    private TextView mobileTv, memoryTv, otherTv;
    private FeaturesViewModel viewModel;
    private SpinnerAdapter spinnerAdapter;
    private FeaturesRepository featureDbRepo;
    public static final String TAG = "MainActivity";
    List<Feature> featuresList;
    List<List<Exclusion>> exclusionList;
    private List<Exclusion> userSelectOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(FeaturesViewModel.class);
        mobilesSpinner = findViewById(R.id.mobileList);
        memorySpinner = findViewById(R.id.memoryList);
        otherSpinner = findViewById(R.id.othersList);
        mobileTv = findViewById(R.id.mobileTv);
        memoryTv = findViewById(R.id.memoryTv);
        otherTv = findViewById(R.id.otherTv);

        setSpinner();
    }

    /*
        used for  getting the data from the API Call
     */
    private void setSpinner() {
        userSelectOption = new ArrayList<>();
        MutableLiveData<FeaturesResponse> resp = viewModel.getFeaturesResponse(this);
        initializeData(resp);
    }

    /*
        Setting up the data into the adapter & getting it back the data on button click
     */
    private void initializeData(MutableLiveData<FeaturesResponse> resp){

        if (resp != null) {
            resp.observe(this, data -> {

                featureDbRepo.insertFeatures(data);

                featuresList = data.getFeaturesList();
                exclusionList = data.getExclusionsList();

                mobileTv.setText(featuresList.get(0).getName());
                memoryTv.setText(featuresList.get(1).getName());
                otherTv.setText(featuresList.get(2).getName());

                spinnerAdapter = new SpinnerAdapter(this, featuresList.get(0).getOptions());
                mobilesSpinner.setAdapter(spinnerAdapter);
                spinnerAdapter = new SpinnerAdapter(this, featuresList.get(1).getOptions());
                memorySpinner.setAdapter(spinnerAdapter);
                spinnerAdapter = new SpinnerAdapter(this, featuresList.get(2).getOptions());
                otherSpinner.setAdapter(spinnerAdapter);

                /*
                    User hits on the submit button
                 */
                findViewById(R.id.submit_main).setOnClickListener(v -> {
                    userSelectOption.add(new Exclusion(
                            featuresList.get(0).getFeatureId(),
                            ((Option) mobilesSpinner.getSelectedItem()).getId()
                    ));
                    userSelectOption.add(new Exclusion(
                            featuresList.get(1).getFeatureId(),
                            ((Option) memorySpinner.getSelectedItem()).getId()
                    ));
                    userSelectOption.add(new Exclusion(
                            featuresList.get(2).getFeatureId(),
                            ((Option) otherSpinner.getSelectedItem()).getId()
                    ));
                    validateUserOptions(userSelectOption,exclusionList);

                });
            });

        } else {
            Toast.makeText(this, "Please try  again . Their was some error", Toast.LENGTH_SHORT).show();
        }
    }

    /*
         This method is used for the validation of the user selected options
     */
    private void validateUserOptions(List<Exclusion> userSelectOption, List<List<Exclusion>> exclusionList) {

        List<List<Exclusion>> userCombinationList = new ArrayList<>();
        List<Exclusion> data = new ArrayList<>();
        int increment = 0;

        data.add(userSelectOption.get(0));
        data.add(userSelectOption.get(1));
        userCombinationList.add(data);
        data = new ArrayList<>();
        data.add(userSelectOption.get(1));
        data.add(userSelectOption.get(2));
        userCombinationList.add(data);
        data = new ArrayList<>();
        data.add(userSelectOption.get(0));
        data.add(userSelectOption.get(2));
        userCombinationList.add(data);

        if(userCombinationList.size() == exclusionList.size()){
            Log.i(TAG, "validateUserOptions GOT INT");
            mainLoop:for(int i=0;i<userCombinationList.size();i++){
                increment=0;
                    for(int j=0;j<exclusionList.size();j++){
                        for(int k=0;k<exclusionList.get(j).size();k++){
                            Exclusion userEx = userCombinationList.get(i).get(k);
                            Exclusion respEx = exclusionList.get(j).get(k);
                            if(userEx.getFeatureId().equalsIgnoreCase( respEx.getFeatureId()) &&
                                   userEx.getOptionsId().equalsIgnoreCase(respEx.getOptionsId())) {
                                increment++;
                                Log.i(TAG, "Condition : " + increment + userEx.getFeatureId() + "\t" + respEx.getFeatureId() + "\n" + userEx.getOptionsId() + "\t" + respEx.getOptionsId());
                            }
                        }
                        if(increment == 2)
                            break mainLoop;
                    }
            }
            if(increment  == 2){
                Toast.makeText(this, "Please select any other option , It is not valid combination", Toast.LENGTH_SHORT).show();
                userSelectOption.clear();
                exclusionList.clear();
                userCombinationList.clear();
                setSpinner();
            }else{
                goToDisplay();
            }
        }

    }

    /*
        Used to store the selected data in the bundle and sending into the Display Activity
     */
    private void goToDisplay() {
        ArrayList<Option> userSelectedOptions = new ArrayList<>();
        userSelectedOptions.add((Option) mobilesSpinner.getSelectedItem());
        userSelectedOptions.add((Option) memorySpinner.getSelectedItem());
        userSelectedOptions.add((Option) otherSpinner.getSelectedItem());

        Log.i(TAG, "goToDisplay: "+userSelectedOptions);

        Intent na = new Intent(MainActivity.this,ResultsView.class);
        na.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(OPTION_DATA,userSelectedOptions);
        na.putExtras(bundle);
        startActivity(na);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Used to check weather their a live internet connection or not
        featureDbRepo = new FeaturesRepository(getApplication());
        hasInternetConnection().subscribe((hasInternet) -> {
            if(!hasInternet) {
                MutableLiveData<FeaturesResponse> resp = new MutableLiveData<>();
                resp.postValue(featureDbRepo.getAllFeatures());
                resp.observe(this,data->{
                    if(data != null){
                        initializeData(resp);
                    }else{
                        Toast.makeText(this, "No Internet Connection & Even any Old Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}




