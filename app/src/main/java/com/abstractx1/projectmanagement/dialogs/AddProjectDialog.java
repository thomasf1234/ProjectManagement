package com.abstractx1.projectmanagement.dialogs;

/**
 * Created by tfisher on 30/01/2017.
 */

import android.widget.EditText;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.abstractx1.androidsql.ModelDAO;
import com.abstractx1.projectmanagement.MainActivity;
import com.abstractx1.projectmanagement.ProjectManagementApplication;
import com.abstractx1.projectmanagement.R;
import com.abstractx1.projectmanagement.db.SchemaV1;
import com.abstractx1.projectmanagement.models.Project;

/**
 * Created by tfisher on 27/10/2016.
 */

public class AddProjectDialog {
    public static AlertDialog create(final MainActivity mainActivity) {
        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);
        View view = layoutInflater.inflate(R.layout.enter_project_name_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mainActivity);

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Enter Project Name");
        alertDialogBuilder.setView(view);

        final EditText editText = (EditText) view.findViewById(R.id.edittext);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ModelDAO modelDAO = new ModelDAO(mainActivity.getApplicationContext(), ProjectManagementApplication.DB_NAME, new SchemaV1());
                Project newProject = new Project();
                newProject.setName(editText.getText().toString());
                try {
                    Project savedProject = modelDAO.save(newProject);
                    mainActivity.syncProjects();
//                    NavigationView navigationView = (NavigationView) mainActivity.findViewById(R.id.nav_view);
//                    Menu menu = navigationView.getMenu();
//                    MenuItem menuItem = menu.add(0, 0, 0, savedProject.getName());
//                    menuItem.setIcon(R.drawable.ic_menu_share);
                    //http://stackoverflow.com/questions/34482404/how-to-add-an-item-to-a-menu-group-in-navigationview
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        return alertDialog;
    }
}



