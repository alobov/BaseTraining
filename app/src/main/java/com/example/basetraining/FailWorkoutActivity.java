package com.example.basetraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Александр on 01.04.2015.
 */
public class FailWorkoutActivity extends Activity {

    private FailWorkoutAdapter mAdapter;
    private DBManager mManager = new DBManager(this);

    private int setCount;
    private int repCount;
    private int mWeekId;
    private String mWorkoutType;
    private Workout mWorkout;
    private String mSetListString;
    private List<Integer> mSetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_workout);

        Week lastWeek = mManager.getLastWeek();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mWorkoutType = extras.getString(ResultActivity.WORKOUT_TYPE);
            setCount = extras.getInt(ResultActivity.SET_COUNT);
            repCount = extras.getInt(ResultActivity.REP_COUNT);
            mWeekId = extras.get(ResultActivity.WEEK_ID)!=null? extras.getInt(ResultActivity.WEEK_ID) : lastWeek.getId();
            mSetListString = extras.get(ResultActivity.SET_LIST_STRING)!=null? extras.getString(ResultActivity.SET_LIST_STRING) : "";
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(mSetListString.equals("")){
            for(int i=0;i<setCount;i++){
                list.add(repCount);
            }
        } else {
            JSONObject json = null;
            try {
                json = new JSONObject(mSetListString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray array =  json.optJSONArray("uniqueArrays");
            int len = array.length();
            for(int i=0;i<len;i++){
                try {
                    list.add(array.getInt(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        ListView mListView = (ListView) findViewById(R.id.fail_sets);
        mAdapter = new FailWorkoutAdapter(getApplicationContext(),R.layout.row_fail,list);
        if(mListView !=null){
            mListView.setAdapter(mAdapter);
        }

        Button mSaveButton = (Button) findViewById(R.id.save_fail);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fail list :", mAdapter.getWorkoutList().toString());
                mWorkout = new Workout(mWeekId, mWorkoutType, mAdapter.getWorkoutList());
                try {
                    if (!mManager.isWorkoutExist(mWeekId, mWorkoutType)) {
                        Log.d("go insert", "ye");
                        mManager.addWorkout(mWorkout);
                    } else {
                        Log.d("go update", "ye");
                        Workout lastWorkout = mManager.getWorkoutByWeekIdAndType(mWeekId, mWorkoutType);
                        mWorkout.setId(lastWorkout.getId());
                        mManager.updateWorkout(mWorkout);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(), TableActivity.class);
                mManager.getAllWorkouts();
                startActivity(i);
                finish();
            }
        });
    }
}
