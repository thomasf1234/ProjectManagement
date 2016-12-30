package com.abstractx1.projectmanagement.table_infos;

import com.abstractx1.projectmanagement.models.Project;

/**
 * Created by tfisher on 30/12/2016.
 */
public class ProjectTableInfo extends ModelTableInfo {
    private static ProjectTableInfo ourInstance = new ProjectTableInfo();
    public static final String TABLE_NAME = "projects";

    public static ProjectTableInfo getInstance() {
        return ourInstance;
    }

    private ProjectTableInfo() {
        super();
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public Class getModelClass() {
        return Project.class;
    }
}
