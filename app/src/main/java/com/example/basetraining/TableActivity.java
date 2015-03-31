package com.example.basetraining;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Александр on 24.03.2015.
 */
public class TableActivity extends Activity{

    private ListView mListView;
    private WeekAdapter mWeekAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        mListView = (ListView)findViewById(R.id.table_result);
        DBManager manager = new DBManager(TableActivity.this);
        List<Week> weekList = manager.getAllWeeks();
        mWeekAdapter = new WeekAdapter(getApplicationContext(), R.layout.row, weekList);
        if(mListView!=null){
            mListView.setAdapter(mWeekAdapter);
        }
    }
}
