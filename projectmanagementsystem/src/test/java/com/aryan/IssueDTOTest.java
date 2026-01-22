package com.aryan;

import com.aryan.model.dto.IssueDTO;
import com.aryan.model.entity.Project;
import com.aryan.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueDTOTest {

    @Test
    public void testIssueDTO() {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(1L);
        issueDTO.setTitle("Issue 1");
        issueDTO.setDescription("Description 1");
        issueDTO.setStatus("Open");
        issueDTO.setProjectId(1L);
        issueDTO.setPriority("High");
        issueDTO.setDueDate(LocalDate.now());
        issueDTO.setTags(new ArrayList<>());
        issueDTO.setProject(new Project());
        issueDTO.setAssignee(new User());

        assertEquals(1L, issueDTO.getId());
        assertEquals("Issue 1", issueDTO.getTitle());
        assertEquals("Description 1", issueDTO.getDescription());
        assertEquals("Open", issueDTO.getStatus());
        assertEquals(1L, issueDTO.getProjectId());
        assertEquals("High", issueDTO.getPriority());
        assertEquals(LocalDate.now(), issueDTO.getDueDate());
        assertEquals(new ArrayList<>(), issueDTO.getTags());
        assertEquals(new Project(), issueDTO.getProject());
        assertEquals(new User(), issueDTO.getAssignee());
    }
}
