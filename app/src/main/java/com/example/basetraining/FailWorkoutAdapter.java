package com.example.basetraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Александр on 01.04.2015.
 */
public class FailWorkoutAdapter extends ArrayAdapter<Integer> {

    private List<Integer> mWorkoutList;
    int mLayoutResourceId;
    Context mContext;

    public FailWorkoutAdapter(Context context, int resource, List<Integer> data) {
        super(context,resource,data);
        mWorkoutList = data;
        mLayoutResourceId = resource;
        mContext = context;
    }

    public List<Integer> getWorkoutList() {
        return mWorkoutList;
    }

    public void setWorkoutList(List<Integer> mWorkoutList) {
        this.mWorkoutList = mWorkoutList;
    }

    @Override
    public Integer getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        TextView setNumber = (TextView)row.findViewById(R.id.set_number);
        TextView repsDec = (TextView)row.findViewById(R.id.reps_dec);
        TextView repsInc = (TextView)row.findViewById(R.id.reps_inc);
        final TextView repsCount = (TextView)row.findViewById(R.id.reps_count);

        setNumber.setText(String.valueOf(position+1));
        repsCount.setText(String.valueOf(mWorkoutList.get(position)));
        repsDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeReps(repsCount, position, -1);
            }
        });
        repsInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeReps(repsCount, position, 1);
            }
        });

        return row;
    }

    public void changeReps(TextView repsCount, int position, int changeValue){
        int count = Integer.parseInt(repsCount.getText().toString());
        if(count+changeValue>=0) {
            repsCount.setText(String.valueOf(count+changeValue));
            count = Integer.parseInt(repsCount.getText().toString());
            mWorkoutList.remove(position);
            mWorkoutList.add(position,count);
        }

    }
}
