package com.abstractx1.projectmanagement.models;

import com.abstractx1.projectmanagement.db.SQLiteSession;
import com.abstractx1.projectmanagement.factories.ModelFactory;
import com.abstractx1.projectmanagement.factories.SqlQueryFactory;
import com.abstractx1.projectmanagement.table_infos.ModelTableInfo;
import com.abstractx1.projectmanagement.table_infos.ProjectTableInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tfisher on 29/12/2016.
 */

//set private variables and still set in factory
public class Project extends BaseModel {
    public String name;

    public static List<Project> getAll() throws NoSuchFieldException, InstantiationException, ParseException, IllegalAccessException {
        List<Object> list = ModelFactory.getAll(ProjectTableInfo.getInstance());
        List<Project> models = new ArrayList<>();
        for (Object obj : list) {
            Project model = (Project) obj;
            models.add(model);
        }

        return models;
    }

    public void save() throws NoSuchFieldException, InstantiationException, ParseException, IllegalAccessException {
        ModelTableInfo modelTableInfo = ProjectTableInfo.getInstance();
        if (hasRecord()) {
//          getChanges() update only changes
//          UPDATE projects set
        } else {
            String insertSQL = SqlQueryFactory.insert(ProjectTableInfo.getInstance(), this);
            this.id = SQLiteSession.getInstance().insert(insertSQL);
            reload();
        }
    }

    public String getName() {
        return name;
    }

    public void reload() throws InstantiationException, IllegalAccessException, ParseException, NoSuchFieldException {
        Project project = (Project) ModelFactory.findById(ProjectTableInfo.getInstance(), id);
        this.created_at = project.created_at;
    }
}
