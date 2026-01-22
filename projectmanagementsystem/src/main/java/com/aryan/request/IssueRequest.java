package com.aryan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class IssueRequest {

    private String title;

    private String description;

    private String status;

    private long projectId;

    private String priority;

    private LocalDateTime dueDate;

}
