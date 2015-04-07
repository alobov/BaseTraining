package com.example.basetraining;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 22.03.2015.
 */
public class Workout {
    int id;
    int weekId;
    String workoutType;
    List<Integer> setList;
    public static final String BENCH = "benchpress";
    public static final String SQUAT = "squat";
    public static final String BENCH_LIGHT = "benchpressLight";
    public static final String SQUAT_LIGHT = "squatLight";
    public static final String DEADLIFT = "deadlift";
    public static final String LIGHT_MODE = "Light";


    public Workout(){
    }

    public Workout(int weekId, String workoutType, List<Integer> setList) {
        this.weekId = weekId;
        this.workoutType = workoutType;
        this.setList = setList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public List<Integer> getSetList() {
        return setList;
    }

    public void setSetList(List<Integer> setList) {
        this.setList = setList;
    }

    public String getSetListString() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("uniqueArrays", new JSONArray(setList));
        String setList = json.toString();
        return setList;
    }
}
