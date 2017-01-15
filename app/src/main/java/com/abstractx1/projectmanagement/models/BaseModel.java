package com.abstractx1.projectmanagement.models;

import com.abstractx1.projectmanagement.db.SQLiteSession;
import com.abstractx1.projectmanagement.factories.ModelFactory;
import com.abstractx1.projectmanagement.factories.SqlQueryFactory;
import com.abstractx1.projectmanagement.table_infos.ModelTableInfo;
import com.abstractx1.projectmanagement.table_infos.ProjectTableInfo;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by tfisher on 29/12/2016.
 */

public abstract class BaseModel {
    public int id;
    public Date created_at;

    public boolean hasRecord() {
        return id != 0;
    }

    public int getId() { return id; }
    public Date getCreatedAt() { return created_at; }
}
