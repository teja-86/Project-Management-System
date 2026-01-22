package com.aryan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    private LocalDateTime createDateTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Issue issue;

}
