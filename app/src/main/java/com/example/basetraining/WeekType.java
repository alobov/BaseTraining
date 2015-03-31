package com.example.basetraining;

/**
 * Created by Александр on 29.03.2015.
 */
public class WeekType {
    String brief;
    int color;
    int sets;
    int reps;

    public WeekType(){
    }

    public WeekType(String brief, int color, int sets, int reps) {
        this.brief = brief;
        this.color = color;
        this.sets = sets;
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}