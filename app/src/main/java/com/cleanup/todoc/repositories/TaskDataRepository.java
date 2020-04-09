package com.cleanup.todoc.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

// repository objective is to isolate data source (DAO) from ViewModel
public class TaskDataRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<Task> mTaskId;

    public TaskDataRepository (Application application){
        TodocDatabase db = TodocDatabase.getInstance(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {return mAllTasks;}

    public void insertTask(Task task){
        TodocDatabase.databaseWriteExecutor.execute(() -> {
                mTaskDao.insertTask(task);
        });
    }

    public void deleteTask(Task task){
        TodocDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.deleteTask(task);
        });
    }
}
