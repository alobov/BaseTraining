package com.example.basetraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.GregorianCalendar;


public class MainActivity extends ActionBarActivity {
    private ImageButton mBenchButton;
    private ImageButton mSquatButton;
    private ImageButton mDeadliftButton;
    public static final String WORKOUT_TYPE_KEY = "workoutType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBenchButton = (ImageButton)findViewById(R.id.bench_button);
        mBenchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ResultActivity.class);
                i.putExtra(WORKOUT_TYPE_KEY,Workout.BENCH);
                startActivity(i);
            }
        });

        mSquatButton = (ImageButton)findViewById(R.id.squat_button);
        mSquatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ResultActivity.class);
                i.putExtra(WORKOUT_TYPE_KEY,Workout.SQUAT);
                startActivity(i);
            }
        });
        mDeadliftButton = (ImageButton)findViewById(R.id.deadlift_button);
        mDeadliftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ResultActivity.class);
                i.putExtra(WORKOUT_TYPE_KEY,Workout.DEADLIFT);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        GregorianCalendar calendar = new GregorianCalendar();
        int currentDay = calendar.get(calendar.DAY_OF_WEEK)-2;
        if(currentDay == -1) currentDay = 6;

        SharedPreferences preferences = getSharedPreferences(ResultActivity.WEEK_PREFERENCES, Context.MODE_PRIVATE);

        if(preferences.getInt(SettingsActivity.BENCH_DAY,0) == currentDay) {
            mBenchButton.setBackgroundResource(R.drawable.rounded_button_hard);
        } else if (preferences.getInt(SettingsActivity.LIGHT_BENCH_DAY,0) == currentDay){
            mBenchButton.setBackgroundResource(R.drawable.rounded_button_light);
        } else {
            mBenchButton.setBackgroundResource(R.drawable.rounded_button);
        }

        if(preferences.getInt(SettingsActivity.SQUAT_DAY,0) == currentDay) {
            mSquatButton.setBackgroundResource(R.drawable.rounded_button_hard);
        } else if (preferences.getInt(SettingsActivity.LIGHT_SQUAT_DAY,0) == currentDay){
            mSquatButton.setBackgroundResource(R.drawable.rounded_button_light);
        } else {
            mSquatButton.setBackgroundResource(R.drawable.rounded_button);
        }

        if(preferences.getInt(SettingsActivity.DEADLIFT_DAY,0) == currentDay) {
            mDeadliftButton.setBackgroundResource(R.drawable.rounded_button_hard);
        } else {
            mDeadliftButton.setBackgroundResource(R.drawable.rounded_button);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_table){
            Intent i = new Intent(getApplicationContext(),TableActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_statistic){
            Intent i = new Intent(getApplicationContext(),StatisticActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_close){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
