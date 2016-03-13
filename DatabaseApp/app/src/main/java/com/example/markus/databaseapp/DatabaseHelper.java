package com.example.markus.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Markus on 13.03.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Freunde.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " integer";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.Freunde.TABLE_NAME + " (" +
                    DatabaseContract.Freunde.COLUMN_NAME_ENTRY_ID + " INT PRIMARY KEY    NOT NULL," +
                    DatabaseContract.Freunde.COLUMN_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    DatabaseContract.Freunde.COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    DatabaseContract.Freunde.COLUMN_NAME_AGE + INT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.Freunde.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertFreund(int id, String vorname, String nachname, int alter){


            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContract.Freunde.COLUMN_NAME_ENTRY_ID, id);
            contentValues.put(DatabaseContract.Freunde.COLUMN_NAME_FIRST_NAME, vorname);
            contentValues.put(DatabaseContract.Freunde.COLUMN_NAME_LAST_NAME, nachname);
            contentValues.put(DatabaseContract.Freunde.COLUMN_NAME_AGE, alter);
            Log.d("D", "davor");
            db.insert(DatabaseContract.Freunde.TABLE_NAME, null, contentValues);
            Log.d("D", "danach");


        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+DatabaseContract.Freunde.TABLE_NAME+" where "+DatabaseContract.Freunde.COLUMN_NAME_ENTRY_ID+"="+id+"", null );
        return res;
    }

    public boolean deleteFreund (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Define 'where' part of query.
        String selection = DatabaseContract.Freunde.COLUMN_NAME_ENTRY_ID + " LIKE ?";
    // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
    // Issue SQL statement.
        db.delete(DatabaseContract.Freunde.TABLE_NAME, selection, selectionArgs);
        return true;
    }

}

