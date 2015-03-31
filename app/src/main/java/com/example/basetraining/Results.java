package com.example.basetraining;

/**
 * Created by Александр on 31.03.2015.
 */
public class Results {
    double benchInc;
    double squatInc;
    double deadliftInc;

    public Results() {
    }

    public Results(double benchInc, double squatInc, double deadliftInc) {
        this.benchInc = benchInc;
        this.squatInc = squatInc;
        this.deadliftInc = deadliftInc;
    }

    public double getBenchInc() {
        return benchInc;
    }

    public void setBenchInc(double benchInc) {
        this.benchInc = benchInc;
    }

    public double getSquatInc() {
        return squatInc;
    }

    public void setSquatInc(double squatInc) {
        this.squatInc = squatInc;
    }

    public double getDeadliftInc() {
        return deadliftInc;
    }

    public void setDeadliftInc(double deadliftInc) {
        this.deadliftInc = deadliftInc;
    }
}
