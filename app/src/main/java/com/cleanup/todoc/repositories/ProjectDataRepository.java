package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

// repository objective is to isolate data source (DAO) from ViewModel
public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao){this.projectDao = projectDao;}

    public LiveData<Project> getUser(long projectId) { return this.projectDao.getProject(projectId);}
}
