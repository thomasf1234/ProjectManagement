package com.abstractx1.projectmanagement.table_infos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abstractx1.projectmanagement.ProjectManagementApplication;
import com.abstractx1.projectmanagement.db.DbHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tfisher on 30/12/2016.
 */

public abstract class ModelTableInfo {
    public static final String TABLE_INFO_COLUMN_NAME = "name";
    public static final String TABLE_INFO_COLUMN_TYPE = "type";
    protected final Map<String, String> columnDataTypes;

    public ModelTableInfo() {
        Map<String, String> _columnDataTypes = new HashMap<String, String>();

        SQLiteDatabase db = DbHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + getTableName() + ")", null);
        if (cursor.moveToFirst()) {
            do {
                String columnName = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_INFO_COLUMN_NAME));
                String dataType = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_INFO_COLUMN_TYPE));
                _columnDataTypes.put(columnName, dataType);
                ProjectManagementApplication.log("retrieving column datatype map name: " + columnName + " type: " + dataType);
            } while (cursor.moveToNext());
        } else {
            throw  new RuntimeException("error accessing table_info for table " + getTableName() );
        }
        cursor.close();
        db.close();

        columnDataTypes = Collections.unmodifiableMap(_columnDataTypes);
    }
    public abstract String getTableName();
    public abstract Class getModelClass();

    public Map<String, String> getColumnDataTypes() {
        return columnDataTypes;
    }
}
