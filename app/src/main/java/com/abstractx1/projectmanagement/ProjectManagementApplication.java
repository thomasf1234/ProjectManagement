package com.abstractx1.projectmanagement;

import android.app.Application;
import android.content.Context;

import com.abstractx1.projectmanagement.db.DbHelper;

/**
 * Created by tfisher on 29/12/2016.
 */

public class ProjectManagementApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initializeDatabase();
    }

    public static Context getAppContext() {
        return mContext;
    }

    private static void initializeDatabase() {
        DbHelper.getInstance().getWritableDatabase().close();
    }
}
