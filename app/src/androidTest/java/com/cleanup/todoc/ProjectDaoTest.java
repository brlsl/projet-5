package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    // FOR DATA
    private TodocDatabase database;
    private Project[] projects = Project.getAllProjects();

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
    public void insertAndGetProject() throws InterruptedException {
        List<Project> projects = LiveDataTestUtil.getValue(this.database.projectDao().getProjects());
        assertTrue(projects.isEmpty());
        this.database.projectDao().insertProject(this.projects[0]);
        this.database.projectDao().insertProject(this.projects[1]);
        this.database.projectDao().insertProject(this.projects[2]);

        projects = LiveDataTestUtil.getValue(this.database.projectDao().getProjects());
        assertEquals(this.projects[0].getColor(), projects.get(0).getColor());
        assertEquals(this.projects[0].getId(), projects.get(0).getId());
        assertEquals(this.projects[0].getName(), projects.get(0).getName());

        assertEquals(this.projects[1].getColor(), projects.get(1).getColor());
        assertEquals(this.projects[1].getId(), projects.get(1).getId());
        assertEquals(this.projects[1].getName(), projects.get(1).getName());

        assertEquals(this.projects[2].getColor(), projects.get(2).getColor());
        assertEquals(this.projects[2].getId(), projects.get(2).getId());
        assertEquals(this.projects[2].getName(), projects.get(2).getName());


    }

}
