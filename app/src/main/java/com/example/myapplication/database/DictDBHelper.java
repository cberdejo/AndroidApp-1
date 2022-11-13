package com.example.myapplication.database;

import static com.example.myapplication.database.DictContract.DictEntry.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import com.example.myapplication.model.Subject;
import com.example.myapplication.model.Task;

import java.util.ArrayList;

public class DictDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;


    public DictDBHelper(Context context, String database_name){
        super(context,database_name,null,DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES_SUBJECT);
        db.execSQL(SQL_CREATE_ENTRIES_TASK);
    }

    // Se reinicia la bd, no soporta varias versiones
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_SUBJECT);
        db.execSQL(SQL_DELETE_ENTRIES_SUBJECT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int i, int i1){
        db.execSQL(SQL_DELETE_ENTRIES_SUBJECT);
        db.execSQL(SQL_DELETE_ENTRIES_TASK);
        onCreate(db);
    }


}
