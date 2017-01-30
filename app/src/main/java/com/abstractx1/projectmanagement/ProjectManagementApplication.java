package com.abstractx1.projectmanagement;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by tfisher on 29/12/2016.
 */

public class ProjectManagementApplication extends Application {
    public static final String DB_NAME = "com.abstractx1.project_management.db";
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
}
