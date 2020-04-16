package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    // FOR DATA
    private TodocDatabase database;
    private Project[] projects = Project.getAllProjects();
    private Task task1 = new Task(projects[0].getId(),"task1", 123);
    private Task task2 = new Task(projects[1].getId(),"task2", 456);
    private Task task3 = new Task(projects[2].getId(),"task3", 789);

    @Before
    public void initDb(){
        this.database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb(){
        database.close();
    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
        assertEquals(0, tasks.size());
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {

        // Add projects and tasks
        this.database.projectDao().insertProject(this.projects[0]);
        this.database.projectDao().insertProject(this.projects[1]);
        this.database.projectDao().insertProject(this.projects[2]);
        this.database.taskDao().insertTask(task1);
        this.database.taskDao().insertTask(task2);
        this.database.taskDao().insertTask(task3);

        // Test
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertFalse(tasks.isEmpty());
        assertEquals(3, tasks.size());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        this.database.projectDao().insertProject(this.projects[0]);
        this.database.projectDao().insertProject(this.projects[1]);
        this.database.projectDao().insertProject(this.projects[2]);
        this.database.taskDao().insertTask(task1);
        this.database.taskDao().insertTask(task2);
        this.database.taskDao().insertTask(task3);


        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertEquals(3, tasks.size());


        this.database.taskDao().deleteTask(tasks.get(0));
        this.database.taskDao().deleteTask(tasks.get(1));

        tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertEquals("task3", tasks.get(0).getName());

    }
}
