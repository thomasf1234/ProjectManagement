package com.abstractx1.projectmanagement;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by tfisher on 30/01/2017.
 */

public class Utilities {
    public static void alert(Activity activity, String message) {
        Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
