package com.aryan.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private String status;

    private long projectID;

    private String priority;

    private LocalDateTime dueDate;

    private List<String> tags = new ArrayList<>();

    @ManyToOne
    private User assignee;

    @JsonIgnore
    @ManyToOne
    private Project project;

    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
