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
    private static final String TABLE_EXERCISE_HISTORY = "EXERCISE_HISTORY";
    private static final String TABLE_RUN_DETAILS = "RUN_DETAILS";
    private static final String TABLE_SITUP_DETAILS = "SITUP_DETAILS";
    private static final String TABLE_PUSHUP_DETAILS = "PUSHUP_DETAILS";
    private static final String TABLE_CHAT_ROOM = "CHAT_ROOM";
    private static final String TABLE_PRIVATE_CHAT_ROOM = "PRIVATE_CHAT_ROOM";
    private static final String TABLE_GROUP_CHAT_ROOM = "GROUP_CHAT_ROOM";
    private static final String TABLE_CHAT_ITEM = "CHAT_ITEM";
    private static final String TABLE_CHAT_MEMBERS = "CHAT_MEMBERS";


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

    // EXERCISE_HISTORY Table - column names
    private static final String EXERCISE_HISTORY_KEY_ID = "exercise_id";
    private static final String EXERCISE_HISTORY_KEY_TYPE = "type";
    private static final String EXERCISE_HISTORY_KEY_CALORIE = "calorie";

    // RUN_DETAILS Table - column names
    private static final String RUN_DETAILS_KEY_DISTANCE = "exercise_id";
    private static final String RUN_DETAILS_KEY_TIME = "total_time";
    private static final String RUN_DETAILS_KEY_STEPS = "steps";

    // SITUP_DETAILS Table - column names
    private static final String SITUP_DETAILS_KEY_TIME = "total_time";
    private static final String SITUP_DETAILS_KEY_COUNT = "count";

    // PUSHUP_DETAILS Table - column names
    private static final String PUSHUP_DETAILS_KEY_TIME = "total_time";
    private static final String PUSHUP_DETAILS_KEY_COUNT = "count";

    // CHAT_ROOM Table - column names
    private static final String CHAT_ROOM_KEY_ID = "chat_id";

    // GROUP_CHAT_ROOM Table - column names
    private static final String GROUP_CHAT_ROOM_KEY_NAME = "name";
    private static final String GROUP_CHAT_ROOM_KEY_PROFILE_PICTURE = "group_picture";

    // CHAT_ITEM Table - column names
    private static final String CHAT_ITEM_KEY_CONTENT = "content";

    private static final String CREATE_TABLE_PERSON =
            "CREATE TABLE " + TABLE_PERSON + "(" +
            COMMON_KEY_USERNAME + " VARCHAR(30) NOT NULL, " +
            PERSON_KEY_REAL_NAME + " VARCHAR(100) NOT NULL, " +
            PERSON_KEY_PROFILE_PICTURE + " VARCHAR(300), " +
            PERSON_KEY_BIRTHDAY + " DATE NOT NULL," +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME +
            ")";

    private static final String CREATE_TABLE_STAT_HISTORY =
            "CREATE TABLE " + TABLE_STAT_HISTORY + "(" +
            COMMON_KEY_USERNAME + " VARCHAR(30), " +
            COMMON_KEY_TIMESTAMP + " TIMESTAMP, " +
            STAT_HISTORY_KEY_HEIGHT + " FLOAT, " +
            STAT_HISTORY_KEY_WEIGHT + " FLOAT, " +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME + ", " + COMMON_KEY_TIMESTAMP + ")," +
            "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
            ")";

    private static final String CREATE_TABLE_EXERCISE_HISTORY =
            "CREATE TABLE " + TABLE_EXERCISE_HISTORY + "(" +
            COMMON_KEY_USERNAME + " VARCHAR(30), " +
            EXERCISE_HISTORY_KEY_ID + "INTEGER AUTO INCREMENT," +
            COMMON_KEY_TIMESTAMP + " TIMESTAMP, " +
            EXERCISE_HISTORY_KEY_TYPE + " VARCHAR(30), " +
            EXERCISE_HISTORY_KEY_CALORIE + " FLOAT, " +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME + ", " + EXERCISE_HISTORY_KEY_ID + ")," +
            "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
            ")";

    private static final String CREATE_TABLE_RUN_DETAILS =
            "CREATE TABLE " + TABLE_RUN_DETAILS + "(" +
                    EXERCISE_HISTORY_KEY_ID + "INTEGER," +
                    RUN_DETAILS_KEY_DISTANCE + " FLOAT, " +
                    RUN_DETAILS_KEY_TIME + " INTEGER, " +
                    RUN_DETAILS_KEY_STEPS + " INTEGER, " +
                    "PRIMARY KEY (" + EXERCISE_HISTORY_KEY_ID + ")," +
                    "FOREIGN KEY (" + EXERCISE_HISTORY_KEY_ID + ") REFERENCES " +
                        TABLE_EXERCISE_HISTORY + "(" + EXERCISE_HISTORY_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_SITUP_DETAILS =
            "CREATE TABLE " + TABLE_SITUP_DETAILS + "(" +
                    EXERCISE_HISTORY_KEY_ID + "INTEGER," +
                    SITUP_DETAILS_KEY_COUNT + " INTEGER, " +
                    SITUP_DETAILS_KEY_TIME + " INTEGER, " +
                    "PRIMARY KEY (" + EXERCISE_HISTORY_KEY_ID + ")," +
                    "FOREIGN KEY (" + EXERCISE_HISTORY_KEY_ID + ") REFERENCES " +
                        TABLE_EXERCISE_HISTORY + "(" + EXERCISE_HISTORY_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_PUSHUP_DETAILS =
            "CREATE TABLE " + TABLE_PUSHUP_DETAILS + "(" +
                    EXERCISE_HISTORY_KEY_ID + "INTEGER," +
                    PUSHUP_DETAILS_KEY_COUNT + " INTEGER, " +
                    PUSHUP_DETAILS_KEY_TIME + " INTEGER, " +
                    "PRIMARY KEY (" + EXERCISE_HISTORY_KEY_ID + ")," +
                    "FOREIGN KEY (" + EXERCISE_HISTORY_KEY_ID + ") REFERENCES " +
                        TABLE_EXERCISE_HISTORY + "(" + EXERCISE_HISTORY_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_CHAT_ROOM =
            "CREATE TABLE " + TABLE_CHAT_ROOM + "(" +
                    CHAT_ROOM_KEY_ID + "INTEGER AUTO INCREMENT," +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_PRIVATE_CHAT_ROOM =
            "CREATE TABLE " + TABLE_PRIVATE_CHAT_ROOM + "(" +
                    CHAT_ROOM_KEY_ID + "INTEGER," +
                    COMMON_KEY_USERNAME + " VARCHAR(30), " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ", " + COMMON_KEY_USERNAME + ")" +
                    "FOREIGN KEY (" + CHAT_ROOM_KEY_ID + ") REFERENCES " +
                        TABLE_CHAT_ROOM + "(" + CHAT_ROOM_KEY_ID + ")" +
                    "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                        TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
                    ")";

    private static final String CREATE_TABLE_GROUP_CHAT_ROOM =
            "CREATE TABLE " + TABLE_GROUP_CHAT_ROOM + "(" +
                    CHAT_ROOM_KEY_ID + "INTEGER," +
                    GROUP_CHAT_ROOM_KEY_NAME + "INTEGER AUTO INCREMENT" +
                    GROUP_CHAT_ROOM_KEY_PROFILE_PICTURE + "VARCHAR(300)" +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_CHAT_MEMBERS =
            "CREATE TABLE " + TABLE_CHAT_MEMBERS + "(" +
                    CHAT_ROOM_KEY_ID + "INTEGER," +
                    COMMON_KEY_USERNAME + " VARCHAR(30), " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ", " + COMMON_KEY_USERNAME + ")" +
                    "FOREIGN KEY (" + CHAT_ROOM_KEY_ID + ") REFERENCES " +
                        TABLE_GROUP_CHAT_ROOM + "(" + CHAT_ROOM_KEY_ID + ")" +
                    "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                        TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
                    ")";

    private static final String CREATE_TABLE_CHAT_ITEM =
            "CREATE TABLE " + TABLE_CHAT_ITEM + "(" +
                    CHAT_ROOM_KEY_ID + "INTEGER," +
                    COMMON_KEY_USERNAME + " VARCHAR(30), " +
                    COMMON_KEY_TIMESTAMP + " TIMESTAMP, " +
                    CHAT_ITEM_KEY_CONTENT + " TEXT, " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ", " + COMMON_KEY_USERNAME + ", "
                        + COMMON_KEY_TIMESTAMP + ")" +
                    "FOREIGN KEY (" + CHAT_ROOM_KEY_ID + ") REFERENCES " +
                        TABLE_GROUP_CHAT_ROOM + "(" + CHAT_ROOM_KEY_ID + ")" +
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
