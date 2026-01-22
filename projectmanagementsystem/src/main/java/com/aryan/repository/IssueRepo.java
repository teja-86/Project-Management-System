package com.aryan.repository;

import com.aryan.model.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepo extends JpaRepository<Issue, Long> {

    public List<Issue> findByProjectId(Long id);

}
