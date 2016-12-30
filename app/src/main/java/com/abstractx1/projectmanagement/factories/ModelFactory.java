package com.abstractx1.projectmanagement.factories;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abstractx1.projectmanagement.ProjectManagementApplication;
import com.abstractx1.projectmanagement.db.DbHelper;
import com.abstractx1.projectmanagement.table_infos.ModelTableInfo;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tfisher on 29/12/2016.
 */

public class ModelFactory {
    public static final String DATA_TYPE_DATETIME = "DATETIME";
    public static final String DATA_TYPE_TEXT = "TEXT";
    public static final String DATA_TYPE_BOOLEAN = "BOOLEAN";
    public static final String DATA_TYPE_INTEGER = "INTEGER";


    public static List<Object> getAll(ModelTableInfo modelTableInfo) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        List<Object> models = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + modelTableInfo.getTableName();

        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ProjectManagementApplication.log("execute SQL projects all");
        if (cursor.moveToFirst()) {
            do {
                Object model = modelTableInfo.getModelClass().newInstance();

                for (String column : modelTableInfo.getColumnDataTypes().keySet()) {
                    String dataType = modelTableInfo.getColumnDataTypes().get(column);

                    if (dataType.equals(DATA_TYPE_INTEGER)) {
                        setIntField(model, column, cursor.getInt(cursor.getColumnIndex(column)));
                    } else if (dataType.equals(DATA_TYPE_BOOLEAN)) {
                        setBoolField(model, column, cursor.getInt(cursor.getColumnIndex(column)));
                    } else if (dataType.equals(DATA_TYPE_TEXT)) {
                        setStringField(model, column, cursor.getString(cursor.getColumnIndex(column)));
                    } else if (dataType.equals(DATA_TYPE_DATETIME)) {
                        setDateField(model, column, cursor.getString(cursor.getColumnIndex(column)));
                    }
                }
                models.add(model);
            } while (cursor.moveToNext());
        }

        return models;
    }

    private static void setIntField(Object object, String fieldName, int value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setInt(object, value);
    }

    private static void setBoolField(Object object, String fieldName, int value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        boolean bool = value > 0 ? true : false;
        field.setBoolean(object, bool);
    }

    private static void setStringField(Object object, String fieldName, String value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.set(object, value);
    }

    private static void setDateField(Object object, String fieldName, String value)
            throws NoSuchFieldException, IllegalAccessException, ParseException {
        Field field = object.getClass().getDeclaredField(fieldName);
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        Date date = format.parse(value);
        field.set(object, date);
    }
}
