package com.example.fitvit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.fitvit.data.StepsContract.stepsEntry;

public class StepsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG  = StepsDbHelper.class.getSimpleName(); // dont know the use but its tradition ;p

    public static final String DATABASE_NAME = "steps.db";
    public static final int DATABASE_VERSION = 1;


    public StepsDbHelper(Context context) {
        super(context, DATABASE_NAME,null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the steps table
        String SQL_CREATE_STEPS_TABLE = "CREATE TABLE " + stepsEntry.TABLE_NAME
                                        + " (" + stepsEntry.COLUMN_DATE + " INTEGER NOT NULL,"
                                        + stepsEntry.COLUMN_STEPS + " INTEGER NOT NULL" + ")";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_STEPS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //The database is still at version 1, so there's nothing to do be done here.

    }
}
