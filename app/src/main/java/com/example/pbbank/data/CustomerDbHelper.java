package com.example.pbbank.data;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CustomerDbHelper extends SQLiteAssetHelper {
    // database Name
    private static final String DATABASE_NAME = "bankCustomer.db";

    // databse version
    private static final int DATABASE_VERSION = 1;

    public CustomerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
