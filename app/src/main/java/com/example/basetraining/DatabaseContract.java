package com.example.basetraining;

/**
 * Created by Александр on 22.03.2015.
 */
public class DatabaseContract {
    public static final String DB_NAME = "base_training.db";

    public abstract class WeekTable {
        public static final String TABLE_NAME = "week_table";


        public static final String WEEK_ID = "_id";
        public static final String IS_BENCH_FAIL = "_is_bench_fail";
        public static final String IS_SQUAT_FAIL = "_is_squat_fail";
        public static final String IS_DEADLIFT_FAIL = "_is_deadlift_fail";
        public static final String BENCH_WEIGHT = "_bench_weight";
        public static final String SQUAT_WEIGHT = "_squat_weight";
        public static final String DEADLIFT_WEIGHT = "_deadlift_weight";
        public static final String LIGHT_BENCH_WEIGHT = "_light_bench_weight";
        public static final String LIGHT_SQUAT_WEIGHT = "_light_squat_weight";
        public static final String IS_COMPLETED = "_is_completed";
        public static final String WEEK_TYPE = "_week_type";
    }

    public abstract class WorkoutTable {
        public static final String TABLE_NAME = "workout_table";

        public static final String ID = "_id";
        public static final String WEEK_ID = "week_id";
        public static final String WORKOUT_TYPE = "workout_type";
        public static final String SET_LIST = "set_list";
    }

    public abstract class WeekType {
        public static final String TABLE_NAME = "week_type";
        public static final String ID = "_id";
        public static final String BRIEF = "brief";
        public static final String COLOR = "color";
        public static final String REPS = "reps";
        public static final String SETS = "sets";
    }
}