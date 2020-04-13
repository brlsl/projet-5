package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

// repository objective is to isolate data source (DAO) from ViewModel
public class ProjectDataRepository {

    private ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao){
        mProjectDao = projectDao;
    }

    public LiveData<List<Project>> getAllProjects(){
        return mProjectDao.getProjects();
    }
}
