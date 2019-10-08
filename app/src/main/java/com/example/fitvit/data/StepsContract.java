package com.example.fitvit.data;

import android.provider.BaseColumns;

public final class StepsContract {

    private StepsContract(){}


    public static final class stepsEntry implements BaseColumns {

        public static final String TABLE_NAME = "steps";


        public static final String COLUMN_DATE = "date";

        public static final String COLUMN_STEPS = "steps";




    }

}
