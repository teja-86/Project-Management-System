package com.aryan.controller;

import com.aryan.model.entity.Comment;
import com.aryan.model.entity.User;
import com.aryan.request.CommentRequest;
import com.aryan.response.ApiResponse;
import com.aryan.service.CommentService;
import com.aryan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestBody CommentRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(req.getIssueId(),
                user.getId(),
                req.getContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        ApiResponse res = new ApiResponse();
        res.setMessage("Comment deleted Successfully!");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{issueId}")
    public ResponseEntity<List<Comment>> findByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.findByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
