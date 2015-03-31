package com.example.basetraining;

/**
 * Created by Александр on 22.03.2015.
 */
public class Workout {
    int id;
    int weekId;
    String workoutType;
    int set_1;
    int set_2;
    int set_3;
    int set_4;
    int set_5;
    public static final String BENCH = "benchpress";
    public static final String SQUAT = "squat";
    public static final String BENCH_LIGHT = "benchpressLight";
    public static final String SQUAT_LIGHT = "squatLight";
    public static final String DEADLIFT = "deadlift";
    public static final String LIGHT_MODE = "Light";


    public Workout(){
    }

    public Workout(int id, int week_id, String workout_type, int set_1, int set_2, int set_3,int set_4, int set_5){
        this.id = id;
        this.weekId = week_id;
        this.workoutType = workout_type;
        this.set_1 = set_1;
        this.set_2 = set_2;
        this.set_3 = set_3;
        this.set_4 = set_4;
        this.set_5 = set_5;
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

    public int getSet_1() {
        return set_1;
    }

    public void setSet_1(int set_1) {
        this.set_1 = set_1;
    }

    public int getSet_2() {
        return set_2;
    }

    public void setSet_2(int set_2) {
        this.set_2 = set_2;
    }

    public int getSet_3() {
        return set_3;
    }

    public void setSet_3(int set_3) {
        this.set_3 = set_3;
    }

    public int getSet_4() {
        return set_4;
    }

    public void setSet_4(int set_4) {
        this.set_4 = set_4;
    }

    public int getSet_5() {
        return set_5;
    }

    public void setSet_5(int set_5) {
        this.set_5 = set_5;
    }
}
