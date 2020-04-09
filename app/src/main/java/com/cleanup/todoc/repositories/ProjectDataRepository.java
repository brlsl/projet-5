package com.cleanup.todoc.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

// repository objective is to isolate data source (DAO) from ViewModel
public class ProjectDataRepository {

    private ProjectDao mProjectDao;

    public ProjectDataRepository(Application application){
        TodocDatabase db = TodocDatabase.getInstance(application);
        mProjectDao = db.projectDao();

    }

    public LiveData<Project> getProject(){return mProjectDao.getProject();}

}
