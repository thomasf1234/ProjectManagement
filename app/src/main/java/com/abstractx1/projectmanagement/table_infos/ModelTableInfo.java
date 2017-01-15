package com.abstractx1.projectmanagement.table_infos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abstractx1.projectmanagement.ProjectManagementApplication;
import com.abstractx1.projectmanagement.SQLite;
import com.abstractx1.projectmanagement.db.SQLiteSession;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tfisher on 30/12/2016.
 */

public abstract class ModelTableInfo {


    public static final HashSet<String> STATIC_COLUMNS = new HashSet<>(Arrays.asList("id", "created_at"));

    protected final Map<String, String> columnTypeNameMap;
    protected final Set<String> nonStaticColumns;


    public ModelTableInfo() {
        Map<String, String> _columnTypeNameMap = new HashMap<String, String>();

        Cursor cursor = SQLiteSession.getInstance().query("PRAGMA table_info(" + getTableName() + ")");
        if (cursor != null) {
            do {
                String columnName = cursor.getString(cursor.getColumnIndexOrThrow(SQLite.TABLE_INFO_COLUMN_NAME));
                String dataType = cursor.getString(cursor.getColumnIndexOrThrow(SQLite.TABLE_INFO_COLUMN_TYPE));
                _columnTypeNameMap.put(columnName, dataType);
                ProjectManagementApplication.log("retrieving column datatype map name: " + columnName + " type: " + dataType);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            throw  new RuntimeException("error accessing table_info for table " + getTableName() );
        }

        columnTypeNameMap = Collections.unmodifiableMap(_columnTypeNameMap);

        nonStaticColumns = new HashSet<>(getColumns());
        nonStaticColumns.removeAll(STATIC_COLUMNS);
    }
    public abstract String getTableName();
    public abstract Class getModelClass();

    public Set<String> getColumns() { return  columnTypeNameMap.keySet(); }
    public Set<String> getNonStaticColumns() { return  nonStaticColumns; }
    public String getColumnTypeName(String column) { return columnTypeNameMap.get(column); }
}
