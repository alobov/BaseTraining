package com.example.basetraining;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by Александр on 01.04.2015.
 */
public class FailWorkoutActivity extends Activity {

    private ListView mListView;
    private Button mSaveButton;
    private FailWorkoutAdapter mAdapter;
    private int setCount = 15;
    private int repCount = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_workout);

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<setCount;i++){
            list.add(repCount);
        }
        mListView = (ListView)findViewById(R.id.fail_sets);
        mAdapter = new FailWorkoutAdapter(getApplicationContext(),R.layout.row_fail,list);
        if(mListView!=null){
            mListView.setAdapter(mAdapter);
        }

        mSaveButton = (Button)findViewById(R.id.save_fail);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fail list :",mAdapter.getWorkoutList().toString());
            }
        });
    }
}
