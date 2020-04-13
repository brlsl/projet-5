package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // récupère liste de tâche
    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getTasks();

    @Query("SELECT * FROM task_table WHERE id = :id")
    LiveData<Task> getTaskId(long id);

    // annotation Insert permet de créer une nouvelle chose à faire de type task
    @Insert
    void insertTask(Task task);

    // permet de supprimer une chose existante en BDD
    @Delete
    void deleteTask(Task task);

}
