package com.example.marvelcharacters.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.marvelcharacters.constants.DataBaseConstants;

public class RegisterDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Register_DataBase.db";

    private static final String SQL_CREATE_TABLE_REGISTER =
            "create TABLE " + DataBaseConstants.LOGIN.TABLE_NAME + " ("
                    + DataBaseConstants.COLUMNS.EMAIL + " TEXT PRIMARY KEY, "
                    + DataBaseConstants.COLUMNS.PASSWORD + " TEXT);";


    public RegisterDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_REGISTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long Login(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("password", password);
        long result = sqLiteDatabase.insert("Login", null, cv);
        return result;

    }

    public String LoginValidation(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Login WHERE email=? AND password=?", new String[]{email, password});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }
}
