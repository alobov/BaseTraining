package com.example.basetraining;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 24.03.2015.
 */
public class WeekAdapter extends ArrayAdapter<Week> {

    private List<Week> mData;
    int mLayoutResourceId;
    Context mContext;

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

        Week week = mData.get(position);
        Log.d("before setting","ok");
        Log.d("week id",String.valueOf(week.getId()));
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
        Log.d("type after get", String.valueOf(week.getWeekType()));
        DBManager manager = new DBManager(mContext);
        String weekType = week.getWeekType();
        int color = manager.getWeekTypeColorByBrief(weekType);
        row.setBackgroundColor(color);
        return row;
    }
}
