package com.example.esperassigment.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.esperassigment.R;
import com.example.esperassigment.model.Option;

import java.util.ArrayList;

import static com.example.esperassigment.util.Constants.OPTION_DATA;

public class ResultsView extends AppCompatActivity {

    public static final String TAG = "ResultView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_view);

        Bundle b = getIntent().getExtras();
        ArrayList<Option> optionList = b.getParcelableArrayList(OPTION_DATA);

        ((TextView)findViewById(R.id.mobileData)).setText(optionList.get(0).getName());
        ((TextView)findViewById(R.id.featureData)).setText(optionList.get(1).getName());
        ((TextView)findViewById(R.id.otherData)).setText(optionList.get(2).getName());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent na = new Intent(this,MainActivity.class);
        na.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(na);
    }
}