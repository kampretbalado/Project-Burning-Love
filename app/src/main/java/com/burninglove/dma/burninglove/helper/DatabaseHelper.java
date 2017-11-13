package com.burninglove.dma.burninglove.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.Telephony.TextBasedSmsColumns.PERSON;

/**
 * Created by malik on 11/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BurningLoveDatabase";

    // Table Names
    private static final String TABLE_PERSON = "PERSON";
    private static final String TABLE_STAT_HISTORY = "STAT_HISTORY";

    // ALL TABLE - common names
    private static final String COMMON_KEY_USERNAME = "username";
    private static final String COMMON_KEY_TIMESTAMP = "timestamp";

    // PERSON Table - column names
    private static final String PERSON_KEY_REAL_NAME = "real_name";
    private static final String PERSON_KEY_PROFILE_PICTURE = "profile_picture";
    private static final String PERSON_KEY_BIRTHDAY = "birthday";

    // STAT_HISTORY Table - column names
    private static final String STAT_HISTORY_KEY_HEIGHT = "height";
    private static final String STAT_HISTORY_KEY_WEIGHT = "weight";

    private static final String CREATE_TABLE_PERSON = "CREATE TABLE " + TABLE_PERSON + "(" +
            COMMON_KEY_USERNAME + " VARCHAR(30) NOT NULL, " +
            PERSON_KEY_REAL_NAME + " VARCHAR(100) NOT NULL, " +
            PERSON_KEY_PROFILE_PICTURE + " VARCHAR(300), " +
            PERSON_KEY_BIRTHDAY + " DATE NOT NULL," +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME +
            ")";

    private static final String CREATE_TABLE_STAT_HISTORY = "CREATE TABLE " + TABLE_STAT_HISTORY + "(" +
            COMMON_KEY_USERNAME + " VARCHAR(30), " +
            COMMON_KEY_TIMESTAMP + " TIMESTAMP, " +
            STAT_HISTORY_KEY_HEIGHT + " FLOAT, " +
            STAT_HISTORY_KEY_WEIGHT + " FLOAT, " +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME + ", " + COMMON_KEY_TIMESTAMP + ")," +
            "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_STAT_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAT_HISTORY);

        onCreate(db);
    }
}
