package com.aryan;

import com.aryan.model.entity.Issue;
import com.aryan.model.entity.Project;
import com.aryan.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IssueTest {

    @Test
    public void testIssue() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Issue 1");
        issue.setDescription("Description 1");
        issue.setStatus("Open");
        issue.setProjectID(1L);
        issue.setPriority("High");
        issue.setDueDate(LocalDateTime.now());
        issue.setTags(new ArrayList<>());
        issue.setAssignee(new User());
        issue.setProject(new Project());
        issue.setComments(new ArrayList<>());

        assertNotNull(issue);
        assertEquals(1L, issue.getId());
        assertEquals("Issue 1", issue.getTitle());
        assertEquals("Description 1", issue.getDescription());
        assertEquals("Open", issue.getStatus());
        assertEquals(1L, issue.getProjectID());
        assertEquals("High", issue.getPriority());
        assertNotNull(issue.getDueDate());
        assertNotNull(issue.getTags());
        assertNotNull(issue.getAssignee());
        assertNotNull(issue.getProject());
        assertNotNull(issue.getComments());
    }
}
