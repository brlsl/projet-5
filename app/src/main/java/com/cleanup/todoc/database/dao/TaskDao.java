package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    // annotation permettant de définir la méthode comme une requête SQL
    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    LiveData<List<Task>> getTask(long projectId);

    // annotation Insert permet de créer une nouvelle chose à faire de type item
    @Insert
    long insertTask(Task task);

    // permet de mettre à jour une chose à faire existante grâce à l'annotation Update
    @Update
    int updateTask(Project project);

    // permet de supprimer une chose existante en BDD
    @Query("DELETE FROM Project WHERE id = :projectId")
    int deleteTask(long projectId);

}
