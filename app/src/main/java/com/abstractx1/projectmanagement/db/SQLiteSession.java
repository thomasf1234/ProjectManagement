package com.abstractx1.projectmanagement.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.abstractx1.projectmanagement.ProjectManagementApplication;

/**
 * Created by tfisher on 29/12/2016.
 */

public class SQLiteSession extends SQLiteOpenHelper {
    private SQLiteSession(Context context) {
        super(context, Schema.DB_NAME, null, Schema.DB_VERSION);
    }

    private static SQLiteSession ourInstance = null;

    public static synchronized SQLiteSession getInstance() {
        return getInstance(ProjectManagementApplication.getAppContext());
    }

    public static synchronized SQLiteSession getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SQLiteSession(context);
        }
        return ourInstance;
    }

    //rawQuery() is for SQL statements that return a result set. Use execSQL() for SQL statements, like INSERT, that do not return a result set.
    public Cursor query(String sql) {
        ProjectManagementApplication.log(String.format("Querying SQL: %s", sql));
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor;
        } else {
            return null;
        }
    }

    public synchronized int insert(String sql) {
        exec(sql);
        Cursor cursor = query("SELECT LAST_INSERT_ROWID() AS id");
        if (cursor == null) {
            throw new RuntimeException("Unable to query LAST_INSERT_ROWID()");
        } else {
            int id = (int) cursor.getLong(cursor.getColumnIndex("id"));
            ProjectManagementApplication.log("Last insert_rowid: " + id);
            return id;
        }
    }

    public synchronized void clear() {
        exec("DELETE FROM projects");
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
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    private synchronized void exec(String sql) {
        ProjectManagementApplication.log(String.format("Executing SQL: %s", sql));
        getWritableDatabase().execSQL(sql);
    }
}

