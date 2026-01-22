package com.aryan.repository;

import com.aryan.model.entity.Project;
import com.aryan.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> { ;

    List<Project> findByProjectNameContainingAndTeamContains(String partialName, User user);

    List<Project> findByTeamContainingOrOwner(User user, User owner);

}
