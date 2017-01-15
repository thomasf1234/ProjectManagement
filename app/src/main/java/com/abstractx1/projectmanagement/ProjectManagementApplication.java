package com.abstractx1.projectmanagement;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.abstractx1.projectmanagement.db.SQLiteSession;

/**
 * Created by tfisher on 29/12/2016.
 */

public class ProjectManagementApplication extends Application {
    private static Context mContext;
    // uncaught exception handler variable
    private Thread.UncaughtExceptionHandler defaultUEH;

    // handler listener
    private Thread.UncaughtExceptionHandler _unCaughtExceptionHandler =
            new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable e) {
                    log(e);
                    // re-throw critical exception further to the os (important)
                    defaultUEH.uncaughtException(thread, e);
                }
            };

    public ProjectManagementApplication() {
        super();
        defaultUEH = Thread.getDefaultUncaughtExceptionHandler();

        // setup handler for uncaught exception
        Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initializeDatabase();
    }

    public static void log(Throwable e) {
        Log.e(getLogKey(), "ERROR", e);
        Log.e(getLogKey(), Log.getStackTraceString(e));
    }

    public static void log(Throwable e, String message) {
        Log.e(getLogKey(), message, e);
        Log.e(getLogKey(), Log.getStackTraceString(e));
    }

    public static void log(String message) {
        Log.v(getLogKey(), message);
    }

    public static String getClassName() {
        return ProjectManagementApplication.class.getSimpleName();
    }
    public static String getLogKey() {
        return "DEBUG-" + getClassName();
    }

    public static Context getAppContext() {
        return mContext;
    }

    private static void initializeDatabase() {
        SQLiteSession.getInstance().getWritableDatabase().close();
    }
}
