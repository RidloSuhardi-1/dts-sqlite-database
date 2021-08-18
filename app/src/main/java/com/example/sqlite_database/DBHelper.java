package com.example.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " +
            TABLE_STUDENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_FIRSTNAME + " TEXT );";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STUDENTS + "'");
        onCreate(db);
    }

    public long addStudentDetail(String student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRSTNAME, student);
        long insert = db.insert(TABLE_STUDENTS, null, contentValues);
        return insert;
    }

    public ArrayList<String> getAllStudentsList() {
        ArrayList<String> studentsArrayList = new ArrayList<String  >();
        String name = "";

        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                studentsArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", studentsArrayList.toString());
        }
        return studentsArrayList;
    }
}
