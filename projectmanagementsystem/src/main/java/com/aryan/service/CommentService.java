package com.aryan.service;

import com.aryan.model.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long issueId, Long userId) throws Exception;

    List<Comment> findByIssueId(Long issueId);

}
