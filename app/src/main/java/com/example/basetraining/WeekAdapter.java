package com.example.basetraining;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Александр on 24.03.2015.
 */
public class WeekAdapter extends ArrayAdapter<Week> {

    private List<Week> mData;
    int mLayoutResourceId;
    Context mContext;
    private Week week;
    private DBManager mManager = new DBManager(getContext());

    public WeekAdapter(Context context, int resource, List<Week> data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }


    @Override
    public Week getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);
        TextView weekView       = (TextView)row.findViewById(R.id.week_cell);
        TextView benchView      = (TextView)row.findViewById(R.id.bench_cell);
        TextView squatView      = (TextView)row.findViewById(R.id.squat_cell);
        TextView deadliftView   = (TextView)row.findViewById(R.id.deadlift_cell);
        TextView lightBenchView = (TextView)row.findViewById(R.id.light_bench_cell);
        TextView lightSquatView = (TextView)row.findViewById(R.id.light_squat_cell);

        week = mData.get(position);

        benchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFailedWorkout(week.isBenchFail(),Workout.BENCH);
            }
        });
        squatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFailedWorkout(week.isSquatFail(),Workout.SQUAT);
            }
        });
        deadliftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFailedWorkout(week.isDeadliftFail(),Workout.DEADLIFT);
            }
        });

        if(week.isBenchFail()!=null){
            Log.d("benchFail ", String.valueOf(week.isBenchFail()));
            if(week.isBenchFail()) benchView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if(week.isSquatFail()!=null){
            Log.d("isSquatFail ", String.valueOf(week.isSquatFail()));
            if(week.isSquatFail()) squatView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if(week.isDeadliftFail()!=null){
            if(week.isDeadliftFail()) deadliftView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        weekView.setText(String.valueOf(week.getId()));
        if (week.getBenchWeight() != null) {
            benchView.setText(week.getBenchWeight().toString());
        } else {
            benchView.setText(String.valueOf("0.00"));
        }
        if (week.getSquatWeight() != null) {
            squatView.setText(week.getSquatWeight().toString());
        } else {
            squatView.setText(String.valueOf("0.00"));
        }
        if (week.getDeadliftWeight() != null) {
            deadliftView.setText(week.getDeadliftWeight().toString());
        } else {
            deadliftView.setText(String.valueOf("0.00"));
        }
        if (week.getLightBenchWeight() != null) {
            lightBenchView.setText(week.getLightBenchWeight().toString());
        } else {
            lightBenchView.setText(String.valueOf("0.00"));
        }
        if (week.getLightSquatWeight() != null) {
            lightSquatView.setText(week.getLightSquatWeight().toString());
        } else {
            lightSquatView.setText(String.valueOf("0.00"));
        }
        DBManager manager = new DBManager(mContext);
        String weekType = week.getWeekType();
        int color = manager.getWeekTypeColorByBrief(weekType);
        row.setBackgroundColor(color);
        return row;
    }

    public void showFailedWorkout(Boolean isFail, String workoutType){
        if(isFail){
            try {
                Workout workout = mManager.getWorkoutByWeekIdAndType(week.getId(), workoutType);
                String listString = workout.getSetListString();
                WeekType weektype = mManager.getWeekTypeByBrief(week.getWeekType());
                Intent i = new Intent(mContext,FailWorkoutActivity.class);
                i.putExtra(ResultActivity.WORKOUT_TYPE,workoutType);
                i.putExtra(ResultActivity.WEEK_ID,workout.getWeekId());
                i.putExtra(ResultActivity.SET_COUNT,weektype.getSets());
                i.putExtra(ResultActivity.REP_COUNT,weektype.getReps());
                i.putExtra(ResultActivity.SET_LIST_STRING,listString);
                mContext.startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
