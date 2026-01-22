package com.aryan;

import com.aryan.model.entity.Comment;
import com.aryan.model.entity.Issue;
import com.aryan.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommentTest {

    @Test
    public void testComment(){
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Comment 1");
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setUser(new User());
        comment.setIssue(new Issue());

        assertNotNull(comment);
        assertEquals(1L, comment.getId());
        assertEquals("Comment 1", comment.getContent());
        assertNotNull(comment.getCreateDateTime());
        assertNotNull(comment.getUser());
        assertNotNull(comment.getIssue());
    }
}
