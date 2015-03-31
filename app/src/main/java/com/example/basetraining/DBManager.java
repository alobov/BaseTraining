package com.example.basetraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 22.03.2015.
 */
public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 26;

    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String BOOLEAN_TYPE = " BOOLEAN";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String SIGNED_TYPE = " SIGNED";
    private static final String COMMA = ", ";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String PRIMARY_KEY_INC = " PRIMARY KEY AUTOINCREMENT";


    private static final String CREATE_WEEK_TABLE = "CREATE TABLE " +
            DatabaseContract.WeekTable.TABLE_NAME + " (" +
            DatabaseContract.WeekTable.WEEK_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA +
            DatabaseContract.WeekTable.IS_BENCH_FAIL + BOOLEAN_TYPE + COMMA +
            DatabaseContract.WeekTable.IS_SQUAT_FAIL + BOOLEAN_TYPE + COMMA +
            DatabaseContract.WeekTable.IS_DEADLIFT_FAIL + BOOLEAN_TYPE + COMMA +
            DatabaseContract.WeekTable.BENCH_WEIGHT + REAL_TYPE + COMMA +
            DatabaseContract.WeekTable.SQUAT_WEIGHT + REAL_TYPE + COMMA +
            DatabaseContract.WeekTable.DEADLIFT_WEIGHT + REAL_TYPE + COMMA +
            DatabaseContract.WeekTable.LIGHT_BENCH_WEIGHT + REAL_TYPE + COMMA +
            DatabaseContract.WeekTable.LIGHT_SQUAT_WEIGHT + REAL_TYPE + COMMA +
            DatabaseContract.WeekTable.IS_COMPLETED + BOOLEAN_TYPE + COMMA +
            DatabaseContract.WeekTable.WEEK_TYPE + TEXT_TYPE + ")";

    private static final String DELETE_WEEK_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.WeekTable.TABLE_NAME;

    private static final String CREATE_WORKOUT_TABLE = "CREATE TABLE " +
            DatabaseContract.WorkoutTable.TABLE_NAME + " (" +
            DatabaseContract.WorkoutTable.ID + INTEGER_TYPE + PRIMARY_KEY_INC + COMMA +
            DatabaseContract.WorkoutTable.WEEK_ID + INTEGER_TYPE + COMMA +
            DatabaseContract.WorkoutTable.SET_1 + INTEGER_TYPE + COMMA +
            DatabaseContract.WorkoutTable.SET_2 + INTEGER_TYPE + COMMA +
            DatabaseContract.WorkoutTable.SET_3 + INTEGER_TYPE + COMMA +
            DatabaseContract.WorkoutTable.SET_4 + INTEGER_TYPE + COMMA +
            DatabaseContract.WorkoutTable.SET_5 + INTEGER_TYPE + COMMA +
            DatabaseContract.WorkoutTable.WORKOUT_TYPE + TEXT_TYPE + ")";

    private static final String DELETE_WORKOUT_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.WorkoutTable.TABLE_NAME;

    private static final String CREATE_WEEKTYPE_TABLE = "CREATE TABLE " +
            DatabaseContract.WeekType.TABLE_NAME + " (" +
            DatabaseContract.WeekType.ID + INTEGER_TYPE + PRIMARY_KEY_INC + COMMA +
            DatabaseContract.WeekType.SETS + INTEGER_TYPE + COMMA +
            DatabaseContract.WeekType.REPS + INTEGER_TYPE + COMMA +
            DatabaseContract.WeekType.BRIEF + TEXT_TYPE + COMMA +
            DatabaseContract.WeekType.COLOR + INTEGER_TYPE + ")";

    private static final String DELETE_WEEKTYPE_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.WeekType.TABLE_NAME;


    public DBManager(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
        Log.d("VERSION", String.valueOf(DATABASE_VERSION));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WEEK_TABLE);
        db.execSQL(CREATE_WORKOUT_TABLE);
        db.execSQL(CREATE_WEEKTYPE_TABLE);
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.WeekType.BRIEF,"5x8");
        values.put(DatabaseContract.WeekType.SETS,5);
        values.put(DatabaseContract.WeekType.REPS,8);
        values.put(DatabaseContract.WeekType.COLOR, Color.parseColor("#00FF00"));
        db.insert(DatabaseContract.WeekType.TABLE_NAME, null, values);
        values.put(DatabaseContract.WeekType.BRIEF,"5x7");
        values.put(DatabaseContract.WeekType.SETS,5);
        values.put(DatabaseContract.WeekType.REPS,7);
        values.put(DatabaseContract.WeekType.COLOR, Color.parseColor("#FFFF00"));
        db.insert(DatabaseContract.WeekType.TABLE_NAME, null, values);
        values.put(DatabaseContract.WeekType.BRIEF,"5x5");
        values.put(DatabaseContract.WeekType.SETS,5);
        values.put(DatabaseContract.WeekType.REPS,5);
        values.put(DatabaseContract.WeekType.COLOR, Color.parseColor("#FF0000"));
        db.insert(DatabaseContract.WeekType.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.d("Database Upgrade", "Database version higher than old.");
            db.execSQL(DELETE_WEEK_TABLE);
            db.execSQL(DELETE_WORKOUT_TABLE);
            db.execSQL(DELETE_WEEKTYPE_TABLE);
            onCreate(db);
        }
    }

    public void addWeek(Week week){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.WeekTable.WEEK_ID, week.getId());
        values.put(DatabaseContract.WeekTable.IS_BENCH_FAIL, week.isBenchFail());
        values.put(DatabaseContract.WeekTable.IS_SQUAT_FAIL, week.isSquatFail());
        values.put(DatabaseContract.WeekTable.IS_DEADLIFT_FAIL, week.isDeadliftFail());
        values.put(DatabaseContract.WeekTable.BENCH_WEIGHT, week.getBenchWeight());
        values.put(DatabaseContract.WeekTable.SQUAT_WEIGHT, week.getSquatWeight());
        values.put(DatabaseContract.WeekTable.DEADLIFT_WEIGHT, week.getDeadliftWeight());
        values.put(DatabaseContract.WeekTable.LIGHT_BENCH_WEIGHT, week.getLightBenchWeight());
        values.put(DatabaseContract.WeekTable.LIGHT_SQUAT_WEIGHT, week.getLightSquatWeight());
        values.put(DatabaseContract.WeekTable.IS_COMPLETED, week.isCompleted());
        values.put(DatabaseContract.WeekTable.WEEK_TYPE,week.getWeekType());
        db.insert(DatabaseContract.WeekTable.TABLE_NAME, null, values);
        db.close();
    }

    public void updateWeek(Week week){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.WeekTable.WEEK_ID, week.getId());
        values.put(DatabaseContract.WeekTable.IS_BENCH_FAIL, week.isBenchFail());
        values.put(DatabaseContract.WeekTable.IS_SQUAT_FAIL, week.isSquatFail());
        values.put(DatabaseContract.WeekTable.IS_DEADLIFT_FAIL, week.isDeadliftFail());
        values.put(DatabaseContract.WeekTable.BENCH_WEIGHT, week.getBenchWeight());
        values.put(DatabaseContract.WeekTable.SQUAT_WEIGHT, week.getSquatWeight());
        values.put(DatabaseContract.WeekTable.DEADLIFT_WEIGHT, week.getDeadliftWeight());
        values.put(DatabaseContract.WeekTable.LIGHT_BENCH_WEIGHT, week.getLightBenchWeight());
        values.put(DatabaseContract.WeekTable.LIGHT_SQUAT_WEIGHT, week.getLightSquatWeight());
        Log.d("onUpdateComp", String.valueOf(week.isCompleted()));
        values.put(DatabaseContract.WeekTable.IS_COMPLETED, week.isCompleted());
        values.put(DatabaseContract.WeekTable.WEEK_TYPE,week.getWeekType());
        db.update(DatabaseContract.WeekTable.TABLE_NAME,values,DatabaseContract.WeekTable.WEEK_ID + " = ?",
                  new String[] {String.valueOf(week.getId())});
        db.close();
    }

    public void addWorkout(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.WorkoutTable.WEEK_ID,workout.getWeekId());
        values.put(DatabaseContract.WorkoutTable.SET_1,workout.getSet_1());
        values.put(DatabaseContract.WorkoutTable.SET_2,workout.getSet_2());
        values.put(DatabaseContract.WorkoutTable.SET_3,workout.getSet_3());
        values.put(DatabaseContract.WorkoutTable.SET_4,workout.getSet_4());
        values.put(DatabaseContract.WorkoutTable.SET_5,workout.getSet_5());
        values.put(DatabaseContract.WorkoutTable.WORKOUT_TYPE,workout.getWorkoutType());
        db.insert(DatabaseContract.WorkoutTable.TABLE_NAME, null, values);
        db.close();
    }

    public void updateWorkout(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.WorkoutTable.WEEK_ID,workout.getWeekId());
        values.put(DatabaseContract.WorkoutTable.SET_1,workout.getSet_1());
        values.put(DatabaseContract.WorkoutTable.SET_2,workout.getSet_2());
        values.put(DatabaseContract.WorkoutTable.SET_3,workout.getSet_3());
        values.put(DatabaseContract.WorkoutTable.SET_4,workout.getSet_4());
        values.put(DatabaseContract.WorkoutTable.SET_5,workout.getSet_5());
        values.put(DatabaseContract.WorkoutTable.WORKOUT_TYPE,workout.getWorkoutType());
        db.update(DatabaseContract.WorkoutTable.TABLE_NAME,values,DatabaseContract.WorkoutTable.ID + " = ?",
                  new String[] {String.valueOf(workout.getId())});
        db.close();
    }
    public List<Week> getAllWeeks(){
        List<Week> weekList = new ArrayList<Week>();
        String selectQuery = "SELECT * FROM " + DatabaseContract.WeekTable.TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Week week = new Week();
                if(cursor.getString(0)!=null) week.setId(Integer.parseInt(cursor.getString(0)));
                if(cursor.getString(1)!=null) week.setBenchFail(Boolean.parseBoolean(cursor.getString(1)));
                if(cursor.getString(2)!=null) week.setSquatFail(Boolean.parseBoolean(cursor.getString(2)));
                if(cursor.getString(3)!=null) week.setDeadliftFail(Boolean.parseBoolean(cursor.getString(3)));
                if(cursor.getString(4)!=null) week.setBenchWeight(Double.parseDouble(cursor.getString(4)));
                if(cursor.getString(5)!=null) week.setSquatWeight(Double.parseDouble(cursor.getString(5)));
                if(cursor.getString(6)!=null) week.setDeadliftWeight(Double.parseDouble(cursor.getString(6)));
                if(cursor.getString(7)!=null) week.setLightBenchWeight(Double.parseDouble(cursor.getString(7)));
                if(cursor.getString(8)!=null) week.setLightSquatWeight(Double.parseDouble(cursor.getString(8)));
                if(cursor.getString(9)!=null) {
                    Boolean completed = cursor.getString(9).equals("0")?false:true;
                    week.setCompleted(completed);
                }
                if(cursor.getString(10)!=null) week.setWeekType(cursor.getString(10));

                weekList.add(week);
            } while (cursor.moveToNext());
        }
        return weekList;
    }
    public int getWeekCount(){
        String selectQuery = "SELECT * FROM " + DatabaseContract.WeekTable.TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        return cursor.getCount();
    }
    public Week getLastWeek(){
        String selectQuery = "SELECT MAX(" + DatabaseContract.WeekTable.WEEK_ID + ")" + COMMA +
                             DatabaseContract.WeekTable.IS_BENCH_FAIL + COMMA +
                             DatabaseContract.WeekTable.IS_SQUAT_FAIL + COMMA +
                             DatabaseContract.WeekTable.IS_DEADLIFT_FAIL + COMMA +
                             DatabaseContract.WeekTable.BENCH_WEIGHT + COMMA +
                             DatabaseContract.WeekTable.SQUAT_WEIGHT + COMMA +
                             DatabaseContract.WeekTable.DEADLIFT_WEIGHT + COMMA +
                             DatabaseContract.WeekTable.LIGHT_BENCH_WEIGHT + COMMA +
                             DatabaseContract.WeekTable.LIGHT_SQUAT_WEIGHT + COMMA +
                             DatabaseContract.WeekTable.IS_COMPLETED + COMMA +
                             DatabaseContract.WeekTable.WEEK_TYPE + " FROM " +
                             DatabaseContract.WeekTable.TABLE_NAME;
        Week week = new Week();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        Log.d("moveToFirst",String.valueOf(cursor.moveToFirst()));
        cursor.moveToFirst();

        Log.d("count", String.valueOf(cursor.getCount()));
        if(cursor.getString(0)!=null) week.setId(Integer.parseInt(cursor.getString(0)));
        if(cursor.getString(1)!=null) week.setBenchFail(Boolean.parseBoolean(cursor.getString(1)));
        if(cursor.getString(2)!=null) week.setSquatFail(Boolean.parseBoolean(cursor.getString(2)));
        if(cursor.getString(3)!=null) week.setDeadliftFail(Boolean.parseBoolean(cursor.getString(3)));
        if(cursor.getString(4)!=null) week.setBenchWeight(Double.parseDouble(cursor.getString(4)));
        if(cursor.getString(5)!=null) week.setSquatWeight(Double.parseDouble(cursor.getString(5)));
        if(cursor.getString(6)!=null) week.setDeadliftWeight(Double.parseDouble(cursor.getString(6)));
        if(cursor.getString(7)!=null) week.setLightBenchWeight(Double.parseDouble(cursor.getString(7)));
        if(cursor.getString(8)!=null) week.setLightSquatWeight(Double.parseDouble(cursor.getString(8)));
        if(cursor.getString(9)!=null) {
            Boolean completed = cursor.getString(9).equals("0")?false:true;
            week.setCompleted(completed);
        }
        Log.d("finished", "true");
        if(cursor.getString(10)!=null) week.setWeekType(cursor.getString(10));
        return week;
    }

    public void addWeekType(WeekType weekType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.WeekType.SETS,weekType.getSets());
        values.put(DatabaseContract.WeekType.REPS,weekType.getReps());
        values.put(DatabaseContract.WeekType.BRIEF,weekType.getBrief());
        values.put(DatabaseContract.WeekType.COLOR,weekType.getColor());
        db.insert(DatabaseContract.WeekType.TABLE_NAME,null,values);
        db.close();
    }

    public void deleteWeekType(int weekId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseContract.WeekType.TABLE_NAME,DatabaseContract.WeekType.ID + " = ?", new String[]{String.valueOf(weekId)});
        db.close();
    }

    public List<String> getWeekTypeBriefs(){
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT "+ DatabaseContract.WeekType.BRIEF + " FROM " + DatabaseContract.WeekType.TABLE_NAME +
                             " ORDER BY "+DatabaseContract.WeekType.SETS + COMMA + DatabaseContract.WeekType.REPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
            do {
                if(cursor.getString(0)!=null) list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        return list;
    }

    public int getWeekTypeColorByBrief(String brief){
        SQLiteDatabase db = this.getReadableDatabase();
        int color = 0;
        String selectQuery = "SELECT COLOR FROM "+DatabaseContract.WeekType.TABLE_NAME + " WHERE " + DatabaseContract.WeekType.BRIEF + " = ?";
        Cursor cursor = db.rawQuery(selectQuery,new String[]{brief});
        cursor.moveToFirst();
        if(cursor.getString(0)!=null) color = Integer.parseInt(cursor.getString(0));
        return color;
    }

    public Results getResultsByParams(String period, String weightType, int weekCount){
        List<Week> weekList = new ArrayList<Week>();
        double benchStart = 0;
        double squatStart = 0;
        double deadliftStart = 0;
        double benchFinish = 0;
        double squatFinish = 0;
        double deadliftFinish = 0;
        Week weekStart;
        Week weekFinish;
        Results results = new Results();

        if(period.equals("С начала")){
            weekList = getAllWeeks();
        } else {
            String selectQuery = "SELECT * FROM " + DatabaseContract.WeekTable.TABLE_NAME +
                    " ORDER BY "+ DatabaseContract.WeekTable.WEEK_ID + " DESC " +
                    " LIMIT "+String.valueOf(weekCount);
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                do{
                    Week week = new Week();
                    if(cursor.getString(0)!=null) week.setId(Integer.parseInt(cursor.getString(0)));
                    if(cursor.getString(1)!=null) week.setBenchFail(Boolean.parseBoolean(cursor.getString(1)));
                    if(cursor.getString(2)!=null) week.setSquatFail(Boolean.parseBoolean(cursor.getString(2)));
                    if(cursor.getString(3)!=null) week.setDeadliftFail(Boolean.parseBoolean(cursor.getString(3)));
                    if(cursor.getString(4)!=null) week.setBenchWeight(Double.parseDouble(cursor.getString(4)));
                    if(cursor.getString(5)!=null) week.setSquatWeight(Double.parseDouble(cursor.getString(5)));
                    if(cursor.getString(6)!=null) week.setDeadliftWeight(Double.parseDouble(cursor.getString(6)));
                    if(cursor.getString(7)!=null) week.setLightBenchWeight(Double.parseDouble(cursor.getString(7)));
                    if(cursor.getString(8)!=null) week.setLightSquatWeight(Double.parseDouble(cursor.getString(8)));
                    if(cursor.getString(9)!=null) {
                        Boolean completed = cursor.getString(9).equals("0")?false:true;
                        week.setCompleted(completed);
                    }
                    if(cursor.getString(10)!=null) week.setWeekType(cursor.getString(10));

                    weekList.add(week);
                } while (cursor.moveToNext());
            }
            ArrayList<Week> auxList = new ArrayList<Week>();
            for(int i=(weekList.size()-1);i>=0;i--){
                auxList.add(weekList.get(i));
            }
            weekList = auxList;
        }

        int weekListSize = weekList.size();
        Log.d("weekListSize", String.valueOf(weekListSize));
        if(weekListSize>0){
            weekStart = weekList.get(0);
            weekFinish = weekList.get(weekListSize-1);
            benchStart = weekStart.getBenchWeight();
            squatStart = weekStart.getSquatWeight();
            deadliftStart = weekStart.getDeadliftWeight();
            benchFinish = weekFinish.getBenchWeight()!=null? weekFinish.getBenchWeight():0;
            squatFinish = weekFinish.getSquatWeight()!=null? weekFinish.getSquatWeight():0;
            Log.d("squatFinish = ",String.valueOf(squatFinish));
            deadliftFinish = weekFinish.getDeadliftWeight()!=null? weekFinish.getDeadliftWeight():0;
            if(weekListSize>1){
                Week prevWeek = weekList.get(weekListSize-2);
                if(benchFinish == 0){
                    Log.d("benchFinish = ",String.valueOf(benchFinish));
                    benchFinish = prevWeek.getBenchWeight();
                    Log.d("benchFinish = ",String.valueOf(benchFinish));
                }
                if(squatFinish == 0){
                    Log.d("squatFinish = ",String.valueOf(squatFinish));
                    squatFinish = prevWeek.getSquatWeight();
                    Log.d("squatFinish = ",String.valueOf(squatFinish));
                }
                if(deadliftFinish == 0){
                    Log.d("deadliftFinish = ",String.valueOf(deadliftFinish));
                    deadliftFinish = prevWeek.getDeadliftWeight();
                    Log.d("deadliftFinish = ",String.valueOf(deadliftFinish));
                }
            }
            results.setBenchInc(benchFinish - benchStart);
            results.setSquatInc(squatFinish - squatStart);
            results.setDeadliftInc(deadliftFinish - deadliftStart);
        } else {
            results.setBenchInc(0.00);
            results.setSquatInc(0.00);
            results.setDeadliftInc(0.00);
        }
        return results;
    }
}
