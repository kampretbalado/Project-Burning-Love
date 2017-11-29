package com.burninglove.dma.burninglove.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.burninglove.dma.burninglove.R;
import com.burninglove.dma.burninglove.models.ChatMessage;
import com.burninglove.dma.burninglove.models.ChatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.provider.Telephony.TextBasedSmsColumns.PERSON;

/**
 * Created by malik on 11/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context myContext;

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
    private static final String RUN_DETAILS_KEY_DISTANCE = "distance";
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
            PERSON_KEY_BIRTHDAY + " DATE NOT NULL, " +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME + ")" +
            ")";

    private static final String CREATE_TABLE_STAT_HISTORY =
            "CREATE TABLE " + TABLE_STAT_HISTORY + "(" +
            COMMON_KEY_USERNAME + " VARCHAR(30) NOT NULL, " +
            COMMON_KEY_TIMESTAMP + " TIMESTAMP NOT NULL, " +
            STAT_HISTORY_KEY_HEIGHT + " FLOAT NOT NULL, " +
            STAT_HISTORY_KEY_WEIGHT + " FLOAT NOT NULL, " +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME + ", " + COMMON_KEY_TIMESTAMP + "), " +
            "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
            ")";

    private static final String CREATE_TABLE_EXERCISE_HISTORY =
            "CREATE TABLE " + TABLE_EXERCISE_HISTORY + "(" +
            COMMON_KEY_USERNAME + " VARCHAR(30) NOT NULL, " +
            EXERCISE_HISTORY_KEY_ID + " INTEGER NOT NULL, " +
            COMMON_KEY_TIMESTAMP + " TIMESTAMP NOT NULL, " +
            EXERCISE_HISTORY_KEY_TYPE + " VARCHAR(30) NOT NULL, " +
            EXERCISE_HISTORY_KEY_CALORIE + " FLOAT NOT NULL, " +
            "PRIMARY KEY (" + COMMON_KEY_USERNAME + ", " + EXERCISE_HISTORY_KEY_ID + "), " +
            "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                TABLE_PERSON + "(" + COMMON_KEY_USERNAME + ")" +
            ")";

    private static final String CREATE_TABLE_RUN_DETAILS =
            "CREATE TABLE " + TABLE_RUN_DETAILS + "(" +
                    EXERCISE_HISTORY_KEY_ID + " INTEGER NOT NULL," +
                    RUN_DETAILS_KEY_DISTANCE + " FLOAT NOT NULL, " +
                    RUN_DETAILS_KEY_TIME + " INTEGER NOT NULL, " +
                    RUN_DETAILS_KEY_STEPS + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + EXERCISE_HISTORY_KEY_ID + "), " +
                    "FOREIGN KEY (" + EXERCISE_HISTORY_KEY_ID + ") REFERENCES " +
                        TABLE_EXERCISE_HISTORY + "(" + EXERCISE_HISTORY_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_SITUP_DETAILS =
            "CREATE TABLE " + TABLE_SITUP_DETAILS + "(" +
                    EXERCISE_HISTORY_KEY_ID + " INTEGER NOT NULL," +
                    SITUP_DETAILS_KEY_COUNT + " INTEGER NOT NULL, " +
                    SITUP_DETAILS_KEY_TIME + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + EXERCISE_HISTORY_KEY_ID + "), " +
                    "FOREIGN KEY (" + EXERCISE_HISTORY_KEY_ID + ") REFERENCES " +
                        TABLE_EXERCISE_HISTORY + "(" + EXERCISE_HISTORY_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_PUSHUP_DETAILS =
            "CREATE TABLE " + TABLE_PUSHUP_DETAILS + "(" +
                    EXERCISE_HISTORY_KEY_ID + " INTEGER NOT NULL, " +
                    PUSHUP_DETAILS_KEY_COUNT + " INTEGER NOT NULL, " +
                    PUSHUP_DETAILS_KEY_TIME + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + EXERCISE_HISTORY_KEY_ID + "), " +
                    "FOREIGN KEY (" + EXERCISE_HISTORY_KEY_ID + ") REFERENCES " +
                        TABLE_EXERCISE_HISTORY + "(" + EXERCISE_HISTORY_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_CHAT_ROOM =
            "CREATE TABLE " + TABLE_CHAT_ROOM + "(" +
                    CHAT_ROOM_KEY_ID + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_PRIVATE_CHAT_ROOM =
            "CREATE TABLE " + TABLE_PRIVATE_CHAT_ROOM + "(" +
                    CHAT_ROOM_KEY_ID + " INTEGER NOT NULL," +
                    COMMON_KEY_USERNAME + " VARCHAR(30) NOT NULL, " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ", " + COMMON_KEY_USERNAME + "), " +
                    "FOREIGN KEY (" + CHAT_ROOM_KEY_ID + ") REFERENCES " +
                        TABLE_CHAT_ROOM + "(" + CHAT_ROOM_KEY_ID + "), " +
                    "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                        TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
                    ")";

    private static final String CREATE_TABLE_GROUP_CHAT_ROOM =
            "CREATE TABLE " + TABLE_GROUP_CHAT_ROOM + "(" +
                    CHAT_ROOM_KEY_ID + " INTEGER NOT NULL, " +
                    GROUP_CHAT_ROOM_KEY_NAME + " INTEGER NOT NULL, " +
                    GROUP_CHAT_ROOM_KEY_PROFILE_PICTURE + " VARCHAR(300), " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ")" +
                    ")";

    private static final String CREATE_TABLE_CHAT_MEMBERS =
            "CREATE TABLE " + TABLE_CHAT_MEMBERS + "(" +
                    CHAT_ROOM_KEY_ID + " INTEGER NOT NULL," +
                    COMMON_KEY_USERNAME + " VARCHAR(30) NOT NULL, " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ", " + COMMON_KEY_USERNAME + "), " +
                    "FOREIGN KEY (" + CHAT_ROOM_KEY_ID + ") REFERENCES " +
                        TABLE_GROUP_CHAT_ROOM + "(" + CHAT_ROOM_KEY_ID + ")," +
                    "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                        TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
                    ")";

    private static final String CREATE_TABLE_CHAT_ITEM =
            "CREATE TABLE " + TABLE_CHAT_ITEM + "(" +
                    CHAT_ROOM_KEY_ID + " INTEGER NOT NULL," +
                    COMMON_KEY_USERNAME + " VARCHAR(30) NOT NULL, " +
                    COMMON_KEY_TIMESTAMP + " TIMESTAMP NOT NULL, " +
                    CHAT_ITEM_KEY_CONTENT + " TEXT NOT NULL, " +
                    "PRIMARY KEY (" + CHAT_ROOM_KEY_ID + ", " + COMMON_KEY_USERNAME + ", "
                        + COMMON_KEY_TIMESTAMP + "), " +
                    "FOREIGN KEY (" + CHAT_ROOM_KEY_ID + ") REFERENCES " +
                        TABLE_GROUP_CHAT_ROOM + "(" + CHAT_ROOM_KEY_ID + "), " +
                    "FOREIGN KEY (" + COMMON_KEY_USERNAME + ") REFERENCES " +
                        TABLE_PERSON+ "(" + COMMON_KEY_USERNAME + ")" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_STAT_HISTORY);
        db.execSQL(CREATE_TABLE_EXERCISE_HISTORY);
        db.execSQL(CREATE_TABLE_RUN_DETAILS);
        db.execSQL(CREATE_TABLE_SITUP_DETAILS);
        db.execSQL(CREATE_TABLE_PUSHUP_DETAILS);
        db.execSQL(CREATE_TABLE_CHAT_ROOM);
        db.execSQL(CREATE_TABLE_PRIVATE_CHAT_ROOM);
        db.execSQL(CREATE_TABLE_GROUP_CHAT_ROOM);
        db.execSQL(CREATE_TABLE_CHAT_ITEM);
        db.execSQL(CREATE_TABLE_CHAT_MEMBERS);

        try {
            insertFromFile(db, myContext, R.raw.db_init);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAT_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUN_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SITUP_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUSHUP_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIVATE_CHAT_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_CHAT_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT_MEMBERS);

        onCreate(db);
    }

    /*
     * Creating a chat message
     * @return chat id
    */
    public long insertChatItem(ChatMessage chat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CHAT_ROOM_KEY_ID, chat.getChatId());
        values.put(COMMON_KEY_USERNAME, chat.getNickname());
        values.put(COMMON_KEY_TIMESTAMP, String.valueOf(chat.getTime().getTime()));
        values.put(CHAT_ITEM_KEY_CONTENT, String.valueOf(chat.getContent()));

        // insert row
        long chat_id = db.insert(TABLE_CHAT_ITEM, null, values);

        return chat_id;
    }

    /*
  * getting all chats
  * */
    public List<ChatMessage> getAllChatsFromId(int chatId) {
        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        String selectQuery =
                "SELECT  * FROM " + TABLE_CHAT_ITEM + " WHERE " + CHAT_ROOM_KEY_ID + "=" + chatId;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                int id = (c.getInt((c.getColumnIndex(CHAT_ROOM_KEY_ID))));
                String username = (c.getString((c.getColumnIndex(COMMON_KEY_USERNAME))));
                java.sql.Timestamp t = new java.sql.Timestamp(c.getInt((c.getColumnIndex(COMMON_KEY_TIMESTAMP))));
                java.util.Date d = new java.util.Date(t.getTime());
                String content = c.getString((c.getColumnIndex(CHAT_ITEM_KEY_CONTENT)));

                ChatMessage cm = new ChatMessage(id, "", content, d, username);

                chatMessages.add(cm);
            } while (c.moveToNext());
        }
        c.close();
        return chatMessages;
    }

    public ChatRoom getPrivateChatRoomFromId(int id) {
        String selectQuery =
                "SELECT * FROM " + TABLE_PRIVATE_CHAT_ROOM +
                        " WHERE " + CHAT_ROOM_KEY_ID + "=" + id;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        c.moveToFirst();
        String username = c.getString(c.getColumnIndex(COMMON_KEY_USERNAME));
        ChatMessage latest = getLatestChatFromId(id);
        ChatRoom cr = new ChatRoom(id, true, username, latest.getContent());
        c.close();
        return cr;
    }

    public ChatMessage getLatestChatFromId(int id) {
        String selectQuery =
                "SELECT * FROM " + TABLE_CHAT_ITEM +
                        " WHERE " + CHAT_ROOM_KEY_ID + "=" + id +
                        " ORDER BY " + COMMON_KEY_TIMESTAMP + " DESC LIMIT 1";
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        c.moveToFirst();
        String username = c.getString(c.getColumnIndex(COMMON_KEY_USERNAME));
        String content  = c.getString(c.getColumnIndex(CHAT_ITEM_KEY_CONTENT));
        java.sql.Timestamp t = new java.sql.Timestamp(c.getInt(c.getColumnIndex(COMMON_KEY_TIMESTAMP)));
        java.util.Date d = new java.util.Date(t.getTime());

        c.close();
        ChatMessage cm = new ChatMessage(id, null, content, d, username);
        return cm;
    }

    public List<ChatRoom> getAllChatRoom() {
        List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();
        String selectQuery =
                "SELECT * FROM " + TABLE_CHAT_ROOM;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int id = (c.getInt((c.getColumnIndex(CHAT_ROOM_KEY_ID))));
                selectQuery =
                        "SELECT * FROM " + TABLE_GROUP_CHAT_ROOM +
                                " WHERE " + CHAT_ROOM_KEY_ID + "=" + id;
                Cursor temp = db.rawQuery(selectQuery, null);
                boolean isPrivateChat = false;
                ChatMessage latest = getLatestChatFromId(id);
                String latestChat = latest.getContent();
                String username;
                if (temp == null) {
                    isPrivateChat = true;
                    username = latest.getNickname();
                } else {
                    isPrivateChat = false;
                    temp.moveToFirst();
                    username = temp.getString(temp.getColumnIndex(GROUP_CHAT_ROOM_KEY_NAME));
                }

                ChatRoom cr = new ChatRoom(id, isPrivateChat, username, latestChat);
                chatRooms.add(cr);
                temp.close();
            } while (c.moveToNext());
        }

        c.close();
        return chatRooms;
    }

    /**
     * This reads a file from the given Resource-Id and calls every line of it as a SQL-Statement
     *
     * @param context
     *
     * @param resourceId
     *  e.g. R.raw.food_db
     *
     * @return Number of SQL-Statements run
     * @throws IOException
     */
    public int insertFromFile(SQLiteDatabase db, Context context, int resourceId) throws IOException {
        // Reseting Counter
        int result = 0;

        // Open the resource
        InputStream insertsStream = context.getResources().openRawResource(resourceId);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        // Iterate through lines (assuming each insert has its own line and theres no other stuff)
        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();
            db.execSQL(insertStmt);
            result++;
        }
        insertReader.close();

        // returning number of inserted rows
        return result;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
