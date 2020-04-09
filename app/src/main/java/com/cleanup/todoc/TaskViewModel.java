package com.cleanup.todoc;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    // REPOSITORIES
    private TaskDataRepository mTaskDataRepository;
    private ProjectDataRepository mProjectDataRepository;

    private LiveData<List<Task>> mAllTasks;

    public TaskViewModel(Application application) {
        super(application);
        mProjectDataRepository = new ProjectDataRepository(application);
        mTaskDataRepository = new TaskDataRepository(application);
        mAllTasks = mTaskDataRepository.getAllTasks();
    }
/*
    public void init(long userId) {
        if (this.currentProject != null) {
            return;
        }
        currentProject = projectDataSource.getUser(userId);
    }
*/
    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<Project> getProject() { return mProjectDataRepository.getProject();}

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getAllTasks() { return mAllTasks;}

    public void createTask(Task task) { mTaskDataRepository.insertTask(task);}

    public void deleteTask(Task task) {mTaskDataRepository.deleteTask(task);}

}