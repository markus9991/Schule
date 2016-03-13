package com.example.markus.databaseapp;

import android.provider.BaseColumns;

/**
 * Created by Markus on 13.03.2016.
 */
public class DatabaseContract {

    public DatabaseContract(){}

    public static abstract class Freunde implements BaseColumns {
        public static final String TABLE_NAME = "Freunde";
        public static final String COLUMN_NAME_ENTRY_ID = "Freund_Id";
        public static final String COLUMN_NAME_FIRST_NAME = "Vorname";
        public static final String COLUMN_NAME_LAST_NAME = "Nachname";
        public static final String COLUMN_NAME_AGE = "Age";
    }
}
