package com.example.basetraining;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Александр on 31.03.2015.
 */
public class StatisticActivity extends Activity {

    private Spinner mPeriodSpinner;
    private Spinner mWeightTypeSpinner;
    private EditText mWeekCount;
    private TextView mBenchInc;
    private TextView mSquatInc;
    private TextView mDeadliftInc;
    private DBManager manager = new DBManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        mPeriodSpinner = (Spinner)findViewById(R.id.select_period);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.dropdown_row_statistic,R.id.dropdown_item,getResources().getStringArray(R.array.periods));
        mPeriodSpinner.setAdapter(adapter);
        mPeriodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItem().toString().equals("С начала")){
                    mWeekCount.setText(String.valueOf(manager.getWeekCount()));
                    mWeekCount.setVisibility(View.INVISIBLE);
                    showResults();
                } else {
                    mWeekCount.setVisibility(View.VISIBLE);
                    showResults();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mWeightTypeSpinner = (Spinner)findViewById(R.id.select_weight_type);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.dropdown_row_statistic,R.id.dropdown_item,getResources().getStringArray(R.array.weight_types));
        mWeightTypeSpinner.setAdapter(adapter1);
        mWeightTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showResults();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mWeekCount = (EditText)findViewById(R.id.last_weeks_count);
        mWeekCount.setVisibility(View.INVISIBLE);
        mWeekCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("onTextChanged","onTextChanged");
                showResults();
            }
        });

        mBenchInc = (TextView)findViewById(R.id.bench_inc);
        mSquatInc = (TextView)findViewById(R.id.squat_inc);
        mDeadliftInc = (TextView)findViewById(R.id.deadlift_inc);
    }

    public void showResults(){
        int weeks = manager.getWeekCount();
        Log.d("weekCount",mWeekCount.getText().toString());
        if(!mWeekCount.getText().toString().equals("")) weeks = Integer.parseInt(mWeekCount.getText().toString())+1;
        Results results = manager.getResultsByParams(mPeriodSpinner.getSelectedItem().toString(),
                mWeightTypeSpinner.getSelectedItem().toString(), weeks);
        String benchInc;
        String squatInc;
        String deadliftInc;
        if(results.getBenchInc()>0){
            benchInc = "+" + String.valueOf(results.getBenchInc());
            mBenchInc.setTextColor(Color.parseColor("#3FE841"));
        } else if(results.getBenchInc()<0) {
            benchInc = String.valueOf(results.getBenchInc());
            mBenchInc.setTextColor(Color.parseColor("#F21D2B"));
        } else {
            benchInc = String.valueOf(results.getBenchInc());
        }

        if(results.getSquatInc()>0){
            squatInc = "+" + String.valueOf(results.getSquatInc());
            mSquatInc.setTextColor(Color.parseColor("#3FE841"));
        } else if(results.getSquatInc()<0){
            squatInc = String.valueOf(results.getSquatInc());
            mSquatInc.setTextColor(Color.parseColor("#F21D2B"));
        } else {
            squatInc = String.valueOf(results.getSquatInc());
        }

        if(results.getDeadliftInc()>0){
            deadliftInc = "+" + String.valueOf(results.getDeadliftInc());
            mDeadliftInc.setTextColor(Color.parseColor("#3FE841"));
        } else if(results.getDeadliftInc()<0){
            deadliftInc = String.valueOf(results.getDeadliftInc());
            mDeadliftInc.setTextColor(Color.parseColor("#F21D2B"));
        } else {
            deadliftInc = String.valueOf(results.getDeadliftInc());
        }

        mBenchInc.setText(benchInc);
        mSquatInc.setText(squatInc);
        mDeadliftInc.setText(deadliftInc);
    }
}
