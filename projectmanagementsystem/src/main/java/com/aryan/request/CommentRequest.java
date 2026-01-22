package com.aryan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommentRequest {

    private Long issueId;

    private String content;

}
