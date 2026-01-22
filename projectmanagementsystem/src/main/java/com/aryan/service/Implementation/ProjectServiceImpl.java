package com.aryan.service.Implementation;

import com.aryan.model.entity.*;
import com.aryan.repository.ProjectRepo;
import com.aryan.repository.SubscriptionRepo;
import com.aryan.service.ChatService;
import com.aryan.service.ProjectService;
import com.aryan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Override
    public Project createProject(Project project, User user) throws Exception {

        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

//        Subscription subscription = subscriptionRepo.findByUserId(user.getId());
//        if (subscription != null && subscription.getPlanType() == PlanType.FREE) {
//            List<Project> userProjects = projectRepo.findByTeamContainingOrOwner(user, user);
//            if (userProjects.size() >= 3) {
//                throw new Exception("Free users can only create up to 3 projects");
//            }
//        }

        Project createdProject = new Project();

        createdProject.setOwner(user);
        createdProject.setTags(project.getTags());
        createdProject.setProjectName(project.getProjectName());
        createdProject.setDescription(project.getDescription());
        createdProject.getTeam().add(user);
        createdProject.setCategory(project.getCategory());

        Project savedProject = projectRepo.save(createdProject);
        Chat chat = new Chat();
        chat.setProject(savedProject);

        Chat projectChat = chatService.createChat(chat);
        savedProject.setChat(projectChat);

        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        List<Project> projects = projectRepo.findByTeamContainingOrOwner(user, user);

        if (category != null) {
            projects = projects.stream().filter(project -> project.getCategory().equals(category))
                    .collect(Collectors.toList());
        }
        if (tag != null) {
            projects = projects.stream().filter(project -> project.getTags().contains(tag))
                    .collect(Collectors.toList());
        }

        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {

        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }

        Optional<Project> project = projectRepo.findById(projectId);
        if(project.isEmpty()){
            throw new Exception("Project Not Found!");
        }
        return project.get();
    }

    @Override
    public Project deleteProject(Long projectId, Long userId) throws Exception {

        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        getProjectById(projectId);
        userService.findUserById(userId);
        projectRepo.deleteById(projectId);

        return null;
    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {

        if (updatedProject == null) {
            throw new IllegalArgumentException("Updated project cannot be null");
        }
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }

        Project project = getProjectById(id);

        project.setProjectName(updatedProject.getProjectName());
        project.setDescription(updatedProject.getDescription());
        project.setCategory(updatedProject.getCategory());
        project.setTags(updatedProject.getTags());

        return projectRepo.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {

        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);

        for (User member : project.getTeam()) {
            if (member.getId().equals(userId)) {
                return;
            }
        }

        project.getChat().getUsers().add(user);
        project.getTeam().add(user);
        projectRepo.save(project);

        System.out.println("-----------" + !project.getTeam().contains(user));
    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {

        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);
        if(project.getTeam().contains(user)){
            project.getChat().getUsers().add(user);
            project.getTeam().remove(user);
        }
        projectRepo.save(project);
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {

        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }

        Project project = getProjectById(projectId);

        return project.getChat();
    }

    @Override
    public List<Project> searchProject(String keyword, User user) throws Exception {

        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        return projectRepo.findByProjectNameContainingAndTeamContains(keyword, user);
    }

}
