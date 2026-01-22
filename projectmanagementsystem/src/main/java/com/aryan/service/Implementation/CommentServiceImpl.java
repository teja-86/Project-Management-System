package com.aryan.service.Implementation;

import com.aryan.model.entity.Comment;
import com.aryan.model.entity.Issue;
import com.aryan.model.entity.User;
import com.aryan.repository.CommentRepo;
import com.aryan.repository.IssueRepo;
import com.aryan.repository.UserRepo;
import com.aryan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private IssueRepo issueRepo;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {

        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        Optional<Issue> issueOptional = issueRepo.findById(issueId);
        Optional<User> userOptional = userRepo.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("Issue not found with ID: " + issueId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("User not found with ID: " + userId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();

        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepo.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepo.findById(commentId);
        Optional<User> userOptional = userRepo.findById(userId);

        if(commentOptional.isEmpty()){
            throw new Exception("Comment not found with ID: " + commentId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("User not found with ID: " + userId);
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if (comment.getUser().equals(user)) {
            commentRepo.delete(comment);
        } else {
            throw new Exception("User does not have Permission to Delete Comment");
        }

    }

    @Override
    public List<Comment> findByIssueId(Long issueId) {
        return commentRepo.findByIssueId(issueId);
    }

}
