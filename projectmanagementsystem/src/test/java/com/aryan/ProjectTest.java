package com.aryan;

import com.aryan.model.entity.Chat;
import com.aryan.model.entity.Project;
import com.aryan.model.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectTest {

    @Test
    public void testProject() {
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("Project 1");
        project.setDescription("Description 1");
        project.setCategory("Category 1");
        project.setTags(new ArrayList<>());
        project.setChat(new Chat());
        project.setOwner(new User());
        project.setIssues(new ArrayList<>());
        project.setTeam(new ArrayList<>());

        assertNotNull(project);
        assertEquals(1L, project.getId());
        assertEquals("Project 1", project.getProjectName());
        assertEquals("Description 1", project.getDescription());
        assertEquals("Category 1", project.getCategory());
        assertNotNull(project.getTags());
        assertNotNull(project.getChat());
        assertNotNull(project.getOwner());
        assertNotNull(project.getIssues());
        assertNotNull(project.getTeam());
    }
}
