package com.aryan.service;

import com.aryan.model.entity.Issue;
import com.aryan.model.entity.User;
import com.aryan.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(long issueId) throws Exception;

    List<Issue> getIssuesByProject(long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(long issueId, Long userid) throws Exception;

    Issue addUserToIssue(long issueId, Long userid) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

}
