package com.abstractx1.projectmanagement.models;

import com.abstractx1.projectmanagement.factories.ModelFactory;
import com.abstractx1.projectmanagement.table_infos.ProjectTableInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tfisher on 29/12/2016.
 */

public class Project extends BaseModel {
    public int id;
    public String name;
    public Date created_at;

    public static List<Project> getAll() throws NoSuchFieldException, InstantiationException, ParseException, IllegalAccessException {
        List<Object> list = ModelFactory.getAll(ProjectTableInfo.getInstance());
        List<Project> models = new ArrayList<>();
        for (Object obj : list) {
            Project model = (Project) obj;
            models.add(model);
        }

        return models;
    }
}
