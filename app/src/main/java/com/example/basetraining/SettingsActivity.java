package com.example.basetraining;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Александр on 29.03.2015.
 */
public class SettingsActivity extends Activity {

    private Button mAddWeekTypeButton;
    private Spinner mWeekTypeSpinner;
    private DBManager mManager = new DBManager(this);
    private WeekType mWeekType;
    private Spinner mBenchSpinner;
    private Spinner mSquatSpinner;
    private Spinner mDeadliftSpinner;
    private Spinner mLightBenchSpinner;
    private Spinner mLightSquatSpinner;

    public static final String BENCH_DAY = "bench_day";
    public static final String SQUAT_DAY = "squat_day";
    public static final String DEADLIFT_DAY = "deadlift_day";
    public static final String LIGHT_BENCH_DAY = "light_bench_day";
    public static final String LIGHT_SQUAT_DAY = "light_squat_day";

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","onResume");
        mWeekTypeSpinner = (Spinner) findViewById(R.id.setting_week_type);
        ArrayList<String> list = (ArrayList<String>) mManager.getWeekTypeBriefs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.dropdown_row,R.id.dropdown_week_type,list);
        mWeekTypeSpinner.setAdapter(adapter);
        mWeekType = new WeekType();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause","onPause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        /**/
        final SharedPreferences preferences = getSharedPreferences(ResultActivity.WEEK_PREFERENCES,Context.MODE_PRIVATE);
        int benchDay = preferences.getInt(BENCH_DAY, 0);
        int squatDay = preferences.getInt(SQUAT_DAY, 0);
        int deadliftDay = preferences.getInt(DEADLIFT_DAY,0);
        int lightBenchDay = preferences.getInt(LIGHT_BENCH_DAY,0);
        int lightSquatDay = preferences.getInt(LIGHT_SQUAT_DAY,0);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.dropdown_row_settings,
                                       R.id.dropdown_week_type,getResources().getStringArray(R.array.daylist));
        mBenchSpinner = (Spinner)findViewById(R.id.daylist_bench);
        mBenchSpinner.setAdapter(adapter);
        mBenchSpinner.setSelection(benchDay);
        mBenchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(BENCH_DAY,position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSquatSpinner = (Spinner)findViewById(R.id.daylist_squat);
        mSquatSpinner.setAdapter(adapter);
        mSquatSpinner.setSelection(squatDay);
        mSquatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(SQUAT_DAY,position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDeadliftSpinner = (Spinner)findViewById(R.id.daylist_deadlift);
        mDeadliftSpinner.setAdapter(adapter);
        mDeadliftSpinner.setSelection(deadliftDay);
        mDeadliftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(DEADLIFT_DAY,position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLightBenchSpinner = (Spinner)findViewById(R.id.daylist_light_bench);
        mLightBenchSpinner.setAdapter(adapter);
        mLightBenchSpinner.setSelection(lightBenchDay);
        mLightBenchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(LIGHT_BENCH_DAY,position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLightSquatSpinner = (Spinner)findViewById(R.id.daylist_light_squat);
        mLightSquatSpinner.setAdapter(adapter);
        mLightSquatSpinner.setSelection(lightSquatDay);
        mLightSquatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(LIGHT_SQUAT_DAY,position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAddWeekTypeButton = (Button)findViewById(R.id.add_week_type);
        mAddWeekTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(SettingsActivity.this);
                d.setContentView(R.layout.add_week_type);
                final Button colorDialogButton = (Button)d.findViewById(R.id.pick_color);
                final EditText dialogSetCount = (EditText)d.findViewById(R.id.dialog_sets);
                final EditText dialogRepCount = (EditText)d.findViewById(R.id.dialog_reps);
                final Button dialogOK = (Button)d.findViewById(R.id.dialog_ok);
                final Button dialogCancel = (Button)d.findViewById(R.id.dialog_cancel);

                colorDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HSVColorPickerDialog cpd = new HSVColorPickerDialog(SettingsActivity.this,0xFF4488CC,new HSVColorPickerDialog.OnColorSelectedListener() {
                            @Override
                            public void colorSelected(Integer color) {
                                Log.d("color = ", String.valueOf(color));
                                mWeekType.setColor(color);
                                colorDialogButton.setBackgroundColor(color);
                            }
                        });
                        cpd.show();
                    }
                });

                dialogOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String setCount = dialogSetCount.getText().toString();
                        String repCount = dialogRepCount.getText().toString();
                        if((!setCount.equals(""))&&(!repCount.equals(""))){
                            mWeekType.setBrief(setCount+"x"+repCount);
                            mWeekType.setSets(Integer.parseInt(setCount));
                            mWeekType.setReps(Integer.parseInt(repCount));
                            mManager.addWeekType(mWeekType);
                            d.dismiss();
                            onResume();
                        } else {
                            Toast.makeText(SettingsActivity.this,R.string.fill_brief,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialogCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                d.show();
            }
        });

    }
}
