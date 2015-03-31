package com.example.basetraining;

import android.text.Editable;

import java.util.ArrayList;

/**
 * Created by Александр on 22.03.2015.
 */
public class Week {
    int _id;
    Boolean _is_bench_fail;
    Boolean _is_squat_fail;
    Boolean _is_deadlift_fail;
    Double _bench_weight;
    Double _squat_weight;
    Double _deadlift_weight;
    Double _light_bench_weight;
    Double _light_squat_weight;
    Boolean _is_completed;
    String _week_type;

    public static final String[] mWeekTypes = {"5x8", "5x7", "5x6", "5x5", "цикл"};

    public Week(){
    }
    public Week(int _id, Boolean _is_bench_fail, Boolean _is_squat_fail, Boolean _is_deadlift_fail,
                Double _bench_weight, Double _squat_weight, Double _deadlift_weight, Double _light_bench_weight,
                Double _light_squat_weight, Boolean _is_completed, String _week_type){
        this._id = _id;
        this._is_bench_fail = _is_bench_fail;
        this._is_squat_fail = _is_squat_fail;
        this._is_deadlift_fail = _is_deadlift_fail;
        this._bench_weight = _bench_weight;
        this._squat_weight = _squat_weight;
        this._deadlift_weight = _deadlift_weight;
        this._light_bench_weight = _light_bench_weight;
        this._light_squat_weight = _light_squat_weight;
        this._is_completed = _is_completed;
        this._week_type = _week_type;
    }

    public Boolean checkFieldsCompleted(){
        if((this.getBenchWeight()!=null)&&(this.getSquatWeight()!=null)&&(this.getDeadliftWeight()!=null)&&
           (this.getLightBenchWeight()!=null)&&(this.getLightSquatWeight()!=null)){
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public Boolean isBenchFail() {
        return _is_bench_fail;
    }

    public void setBenchFail(Boolean _is_bench_fail) {
        this._is_bench_fail = _is_bench_fail;
    }

    public Boolean isSquatFail() {
        return _is_squat_fail;
    }

    public void setSquatFail(Boolean _is_squat_fail) {
        this._is_squat_fail = _is_squat_fail;
    }

    public Boolean isDeadliftFail() {
        return _is_deadlift_fail;
    }

    public void setDeadliftFail(Boolean _is_deadlift_fail) {
        this._is_deadlift_fail = _is_deadlift_fail;
    }

    public Double getBenchWeight() {
        return _bench_weight;
    }

    public void setBenchWeight(Double _bench_weight) {
        this._bench_weight = _bench_weight;
    }

    public Double getSquatWeight() {
        return _squat_weight;
    }

    public void setSquatWeight(Double _squat_weight) {
        this._squat_weight = _squat_weight;
    }

    public Double getDeadliftWeight() {
        return _deadlift_weight;
    }

    public void setDeadliftWeight(Double _deadlift_weight) {
        this._deadlift_weight = _deadlift_weight;
    }

    public Double getLightBenchWeight() {
        return _light_bench_weight;
    }

    public void setLightBenchWeight(Double _light_bench_weight) {
        this._light_bench_weight = _light_bench_weight;
    }

    public Double getLightSquatWeight() {
        return _light_squat_weight;
    }

    public void setLightSquatWeight(Double _light_squat_weight) {
        this._light_squat_weight = _light_squat_weight;
    }

    public Boolean isCompleted() {
        return _is_completed;
    }

    public void setCompleted(Boolean _is_completed) {
        this._is_completed = _is_completed;
    }

    public String getWeekType() {
        return _week_type;
    }

    public void setWeekType(String _week_type) {
        this._week_type = _week_type;
    }

    public void setWorkout(String workoutType,String text){
        switch(workoutType) {
            case Workout.BENCH       : this.setBenchWeight(Double.parseDouble(text));
                                       this.setBenchFail(false);
                                       break;
            case Workout.BENCH_LIGHT : this.setLightBenchWeight(Double.parseDouble(text));
                                       break;
            case Workout.SQUAT_LIGHT : this.setLightSquatWeight(Double.parseDouble(text));
                                       break;
            case Workout.SQUAT       : this.setSquatWeight(Double.parseDouble(text));
                                       this.setSquatFail(false);
                                       break;
            case Workout.DEADLIFT    : this.setDeadliftWeight(Double.parseDouble(text));
                                       this.setDeadliftFail(false);
                                       break;
            default                  : this.setBenchWeight(Double.parseDouble(text));
                                       this.setBenchFail(false);
                                       break;
        }
    }
}
