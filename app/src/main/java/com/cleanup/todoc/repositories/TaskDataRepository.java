package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

// repository objective is to isolate data source (DAO) from ViewModel
public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository (TaskDao taskDao){this.taskDao = taskDao;}


    public LiveData<List<Task>> getTask(long projectId){return this.taskDao.getTask(projectId);}

    public void createTask(Task task){taskDao.insertTask(task);}

    public void updateTask(Task task){taskDao.updateTask(task);}

    public void deleteTask(long taskId){taskDao.deleteTask(taskId);}

}
