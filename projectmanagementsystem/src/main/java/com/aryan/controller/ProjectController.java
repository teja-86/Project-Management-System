package com.aryan.controller;

import com.aryan.model.entity.Chat;
import com.aryan.model.entity.Invitation;
import com.aryan.model.entity.Project;
import com.aryan.model.entity.User;
import com.aryan.request.InvitationRequest;
import com.aryan.response.ApiResponse;
import com.aryan.service.ChatService;
import com.aryan.service.Implementation.ChatServiceImpl;
import com.aryan.service.InvitationService;
import com.aryan.service.ProjectService;
import com.aryan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatServiceImpl chatServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<Project>>getProjects(

            @RequestParam(required = false)String category,
            @RequestParam(required = false)String tag,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project>getProjectById(

            @PathVariable long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Project projects = projectService.getProjectById(projectId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Project>createProject(

            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project>updateProject(

            @PathVariable long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Project updateProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updateProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Project>deleteProject(

            @PathVariable long projectId,
            @RequestHeader("Authorization") String jwt

    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Project deleteProject = projectService.deleteProject(projectId, user.getId());
        ApiResponse res = new ApiResponse("Project Deleted Successfully!");

        return new ResponseEntity<>(deleteProject, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>>searchProjects(

            @RequestParam(required = false)String keyword,

            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        List<Project> projects = projectService.searchProject(keyword,user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
        public ResponseEntity<Chat>getChatByProjectId(

                @PathVariable long projectId,
                @RequestHeader("Authorization") String jwt
        ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<ApiResponse>inviteProject(

            @RequestBody InvitationRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());
        ApiResponse res = new ApiResponse("User Invitation Sent!");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation>acceptInviteProject(

            @RequestParam String token,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }

}
