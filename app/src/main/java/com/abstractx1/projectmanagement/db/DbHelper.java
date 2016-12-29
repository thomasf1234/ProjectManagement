package com.abstractx1.projectmanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.abstractx1.projectmanagement.ProjectManagementApplication;

/**
 * Created by tfisher on 29/12/2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    private DbHelper(Context context) {
        super(context, Schema.DB_NAME, null, Schema.DB_VERSION);
    }

    private static DbHelper ourInstance = null;

    public static synchronized DbHelper getInstance() {
        return getInstance(ProjectManagementApplication.getAppContext());
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DbHelper(context);
        }
        return ourInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Schema.create(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        } else {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}

