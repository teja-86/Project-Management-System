package com.aryan.service.Implementation;

import com.aryan.model.entity.Issue;
import com.aryan.model.entity.Project;
import com.aryan.model.entity.User;
import com.aryan.repository.IssueRepo;
import com.aryan.request.IssueRequest;
import com.aryan.service.IssueService;
import com.aryan.service.ProjectService;
import com.aryan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepo issueRepo;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Issue getIssueById(long issueId) throws Exception {
        Optional<Issue> issue = issueRepo.findById(issueId);
        if (issue.isPresent()){
            return issue.get();
        }
        throw new Exception("No issue found with Issue ID: " + issueId);
    }

    @Override
    public List<Issue> getIssuesByProject(long projectId) throws Exception {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        return issueRepo.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {

        if (issueRequest == null) {
            throw new IllegalArgumentException("Issue request cannot be null");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Project project = projectService.getProjectById(issueRequest.getProjectId());

        if (project == null) {
            throw new IllegalArgumentException("Project not found for ID: " + issueRequest.getProjectId());
        }

        Issue issue = new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setProjectID(issue.getProjectID());
        issue.setPriority(issueRequest.getPriority());
        issue.setDueDate(issueRequest.getDueDate());

        issue.setProject(project);
        return issueRepo.save(issue);
    }

    @Override
    public void deleteIssue(long issueId, Long userid) throws Exception {

        if (issueId <= 0) {
            throw new IllegalArgumentException("Invalid issue ID");
        }
        if (userid == null || userid <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        getIssueById(issueId);
        issueRepo.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(long issueId, Long userid) throws Exception {

        if (issueId <= 0) {
            throw new IllegalArgumentException("Invalid issue ID");
        }
        if (userid == null || userid <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        User user = userService.findUserById(userid);
        if (user == null) {
            throw new IllegalArgumentException("User not found for ID: " + userid);
        }

        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);

        return issueRepo.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {

        if (issueId == null || issueId <= 0) {
            throw new IllegalArgumentException("Invalid issue ID");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        Issue issue = getIssueById(issueId);
        issue.setStatus(status);
        return issueRepo.save(issue);
    }

}
