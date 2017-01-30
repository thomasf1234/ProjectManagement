package com.abstractx1.projectmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.abstractx1.projectmanagement.R;
import com.abstractx1.projectmanagement.models.Project;

import java.util.List;

/**
 * Created by tfisher on 30/01/2017.
 */

public class ProjectsListViewAdapter extends ArrayAdapter<Project> {
    public ProjectsListViewAdapter(Context context, List<Project> projects) {
        super(context, 0, projects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Project project = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.projects_listview_element, parent, false);
        }
        TextView projectName = (TextView) convertView.findViewById(R.id.projectName);
        projectName.setText(project.getName());

        return convertView;
    }
}
