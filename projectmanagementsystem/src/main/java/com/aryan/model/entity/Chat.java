package com.aryan.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne
    private Project project;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> message;

    @ManyToMany
    private List<User> users = new ArrayList<>();

}
