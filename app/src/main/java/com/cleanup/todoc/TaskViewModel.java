package com.cleanup.todoc;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository mTaskDataSource;
    private final ProjectDataRepository mProjectDataSource;
    private final Executor mExecutor;

    // DATA
    @Nullable
    private LiveData<List<Project>> mProjectList;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        mTaskDataSource = taskDataSource;
        mProjectDataSource = projectDataSource;
        mExecutor = executor;
    }

    public void init() {
        if (mProjectList == null) {
            mProjectList = mProjectDataSource.getAllProjects();
        }
    }

    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<List<Project>> getAllProject() {return mProjectList;}

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getAllTasks() {
        return mTaskDataSource.getAllTasks();
    }

    public void insertTask(Task task) {
        mExecutor.execute(() ->
            mTaskDataSource.insertTask(task));
    }

    public void deleteTask(Task task) {
        mExecutor.execute(() -> {
            mTaskDataSource.deleteTask(task);
        });
    }
}