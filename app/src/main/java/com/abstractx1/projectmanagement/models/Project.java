package com.abstractx1.projectmanagement.models;

import com.abstractx1.androidsql.BaseModel;
import com.abstractx1.androidsql.Column;
import com.abstractx1.androidsql.TableName;

import java.util.Date;

/**
 * Created by tfisher on 30/01/2017.
 */

@TableName("projects")
public class Project extends BaseModel {
    @Column(name = "name") private String name;
    @Column(name = "created_at", readOnly = true) private Date createdAt;

    public Project() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
