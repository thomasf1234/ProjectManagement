package com.abstractx1.projectmanagement.factories;

import com.abstractx1.projectmanagement.SQLite;
import com.abstractx1.projectmanagement.models.BaseModel;
import com.abstractx1.projectmanagement.table_infos.ModelTableInfo;

import java.lang.reflect.Field;
import java.text.ParseException;

/**
 * Created by tfisher on 03/01/2017.
 */

public class SqlQueryFactory {
    public static String insert(ModelTableInfo modelTableInfo, BaseModel model) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        boolean isFirst = true;
        for (String column : modelTableInfo.getNonStaticColumns()) {
            String typeName = modelTableInfo.getColumnTypeName(column);
            Field field = model.getClass().getDeclaredField(column);
            String value = SQLite.toString(field.get(model), typeName);

            if (isFirst) {
                isFirst = false;
                columns.append(column);
                values.append(value);
            } else {
                columns.append(",");
                columns.append(column);
                values.append(",");
                values.append(value);
            }
        }

        String query =  "INSERT INTO " + modelTableInfo.getTableName() + " (" + columns + ") " + "VALUES (" + values + ")";
        return query;
    }
}
