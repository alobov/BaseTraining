package com.example.basetraining;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 21.03.2015.
 */
public class ResultActivity extends Activity{
    private Button mIncLargeButton;
    private Button mIncSmallButton;
    private Button mDecLargeButton;
    private Button mDecSmallButton;
    private ImageButton mAcceptWorkoutButton;
    private ImageButton mFailWorkoutButton;
    private CheckBox mCheckboxLight;
    private TextView mResultHeader;
    private String mWorkoutType;
    private Spinner mWeekTypeSpinner;
    private Boolean mLastWeekCompleted = false;
    private int mWeekCount;
    private Week mLastWeek;
    private static final Double incLarge = new Double("5");
    private static final Double incSmall = new Double("2.5");
    private static final Double decLarge = new Double("-5");
    private static final Double decSmall = new Double("-2.5");
    private DBManager mManager = new DBManager(this);

    public static final String WEEK_PREFERENCES = "week_preferences";
    public static final String WEEKTYPE_SETTING = "weektype_setting";

    private EditText mResultText;

    private String mWeekType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_result);
        /**
         * Получение типа тренировки из предыдушей активности
         */
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String workoutType = extras.getString(MainActivity.WORKOUT_TYPE_KEY);
            if(workoutType!=null){
                mWorkoutType = workoutType;
            }
        }
        mResultHeader = (TextView)findViewById(R.id.result_header);
        setHeader(mWorkoutType,mResultHeader);

        /**
         * Получение настроек приложения
         * 1) тип текущей недели
         * 2) TODO : список типов недель
         * 3) TODO : список цветов для типов недель
         */
        final SharedPreferences preferences = getSharedPreferences(WEEK_PREFERENCES, Context.MODE_PRIVATE);
        mWeekType = preferences.getString(WEEKTYPE_SETTING,"5x8");

        /**
         *
         */
        mCheckboxLight = (CheckBox) findViewById(R.id.checkbox_light);
        mCheckboxLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("type = ",mWorkoutType);
                Log.d("typeW = ",Workout.BENCH_LIGHT);
                Log.d("is light? ", String.valueOf((mWorkoutType.equals(Workout.BENCH_LIGHT))));

                if(isChecked){
                    if(!mWorkoutType.equals(Workout.DEADLIFT))
                       mWorkoutType = mWorkoutType + Workout.LIGHT_MODE;
                } else {
                    if(mWorkoutType.equals(Workout.BENCH_LIGHT)){
                        mWorkoutType = Workout.BENCH;
                    }
                    if(mWorkoutType.equals(Workout.SQUAT_LIGHT)){
                        mWorkoutType = Workout.SQUAT;
                    }
                }
                Log.d("type = ",mWorkoutType);
            }
        });
        if(mCheckboxLight.isChecked()==true){
            mWorkoutType = mWorkoutType + Workout.LIGHT_MODE;
        }

        mResultText = (EditText)findViewById(R.id.result_text);
        List<Week> auxWeekList = mManager.getAllWeeks();
        int auxListSize = auxWeekList.size();
        Week auxLastWeek;
        if(auxListSize>1){
            if(auxWeekList.get(auxListSize-1).isCompleted()==true){
                auxLastWeek = auxWeekList.get(auxListSize-1);
            } else {
                auxLastWeek = auxWeekList.get(auxListSize-2);
            }
            switch (mWorkoutType) {
                case Workout.BENCH : mResultText.setText(String.valueOf(auxLastWeek.getBenchWeight()));
                    break;
                case Workout.SQUAT : mResultText.setText(auxLastWeek.getSquatWeight().toString());
                    break;
                case Workout.DEADLIFT : mResultText.setText(auxLastWeek.getDeadliftWeight().toString());
                    break;
                default: mResultText.setText("50");
                    break;
            }
        }else if(auxListSize == 1){
            auxLastWeek = mManager.getLastWeek();
            switch (mWorkoutType) {
                case Workout.BENCH : mResultText.setText(String.valueOf(auxLastWeek.getBenchWeight()));
                    break;
                case Workout.SQUAT : mResultText.setText(auxLastWeek.getSquatWeight().toString());
                    break;
                case Workout.DEADLIFT : mResultText.setText(auxLastWeek.getDeadliftWeight().toString());
                    break;
                default: mResultText.setText("50");
                    break;
            }
        }

        mIncLargeButton = (Button)findViewById(R.id.inc_large_button);
        mIncLargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double d = Double.parseDouble(mResultText.getText().toString());
                Double result = d+incLarge;
                mResultText.setText(result.toString());
            }
        });
        mIncSmallButton = (Button)findViewById(R.id.inc_small_button);
        mIncSmallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double d = Double.parseDouble(mResultText.getText().toString());
                Double result = d+incSmall;
                mResultText.setText(result.toString());
            }
        });
        mDecLargeButton = (Button)findViewById(R.id.dec_large_button);
        mDecLargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double d = Double.parseDouble(mResultText.getText().toString());
                Double result = d+decLarge;
                mResultText.setText(result.toString());
            }
        });
        mDecSmallButton = (Button)findViewById(R.id.dec_small_button);
        mDecSmallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double d = Double.parseDouble(mResultText.getText().toString());
                Double result = new BigDecimal(d+decSmall).setScale(1, RoundingMode.UP).doubleValue();
                mResultText.setText(result.toString());
            }
        });
        mWeekTypeSpinner = (Spinner)findViewById(R.id.spinner_week_type);
        ArrayList<String> list = (ArrayList<String>) mManager.getWeekTypeBriefs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResultActivity.this,R.layout.dropdown_row,R.id.dropdown_week_type,list);
        mWeekTypeSpinner.setAdapter(adapter);
        for(int i=0;i<mWeekTypeSpinner.getCount();i++){
            if(mWeekTypeSpinner.getItemAtPosition(i).equals(mWeekType)){
                mWeekTypeSpinner.setSelection(i);
                break;
            }
        }
        mWeekTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String brief = parent.getSelectedItem().toString();
                int color = mManager.getWeekTypeColorByBrief(brief);
                mWeekTypeSpinner.setBackgroundColor(color);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(WEEKTYPE_SETTING,brief);
                editor.apply();
                mWeekType = brief;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mAcceptWorkoutButton = (ImageButton) findViewById(R.id.workout_accept_button);
        mAcceptWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mWeekCount ", String.valueOf(mWeekCount));
                Log.d("mLastWeekCompleted ", String.valueOf(mLastWeekCompleted));
                if((mWeekCount == 0)||(mLastWeekCompleted == true)){
                    Week newWeek = addNewWeek(mWeekCount);
                    mManager.addWeek(newWeek);
                } else if(mLastWeekCompleted == false){
                    updateCurrentWeek(mLastWeek);
                    mManager.updateWeek(mLastWeek);
                } else {
                    Toast.makeText(getApplicationContext(),"SOMETHING WENT WRONG",Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(v.getContext(),TableActivity.class);
                startActivity(i);
            }
        });
        mFailWorkoutButton = (ImageButton) findViewById(R.id.workout_fail_button);
        mFailWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mWeekCount = mManager.getWeekCount();
        Log.d("mWeekCount ", String.valueOf(mWeekCount));
        if(mWeekCount != 0) {
            mLastWeek = mManager.getLastWeek();
            Boolean lastWeekCompleted = mLastWeek!=null? mLastWeek.isCompleted():false;
            mWeekTypeSpinner.setEnabled(lastWeekCompleted);
            mLastWeekCompleted = lastWeekCompleted;
        } else {
            mWeekTypeSpinner.setEnabled(true);
        }
    }

    private Week addNewWeek(int newWeekId){
        Week newWeek = new Week();
        newWeek.setId(newWeekId);
        Log.d("mWorkoutType ",mWorkoutType);
        newWeek.setWorkout(mWorkoutType, String.valueOf(mResultText.getText()));
        newWeek.setCompleted(false);
        Log.d("type before add", String.valueOf(mWeekType));
        newWeek.setWeekType(mWeekTypeSpinner.getSelectedItem().toString());
        return newWeek;
    }
    private void updateCurrentWeek(Week currentWeek){
        currentWeek.setWorkout(mWorkoutType, String.valueOf(mResultText.getText()));
        Log.d("correntWC",String.valueOf(currentWeek.checkFieldsCompleted()));
        if(currentWeek.checkFieldsCompleted() == true){
            currentWeek.setCompleted(true);
        }
    }
    private void setHeader(String workoutType,TextView view){
        switch(workoutType){
            case Workout.BENCH : view.setText(R.string.add_bench);
                                 break;
            case Workout.SQUAT : view.setText(R.string.add_squat);
                                 break;
            case Workout.DEADLIFT : view.setText(R.string.add_deadlift);
                                    break;
            default: view.setText(R.string.add_workout);
                     break;
        }
    }
}