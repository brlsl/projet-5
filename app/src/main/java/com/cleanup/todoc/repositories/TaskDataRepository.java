package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

// repository objective is to isolate data source (DAO) from ViewModel
public class TaskDataRepository {

    private TaskDao mTaskDao;

    public TaskDataRepository (TaskDao taskDao){
        this.mTaskDao = taskDao;
    }

    public LiveData<List<Task>> getAllTasks() {
        return mTaskDao.getTasks();
    }

    public void insertTask(Task task) {
        mTaskDao.insertTask(task);
    }

    public void deleteTask(Task task) {
        mTaskDao.deleteTask(task);
    }
}
