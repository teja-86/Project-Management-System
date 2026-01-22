package com.aryan.controller;

import com.aryan.config.JwtProvider;
import com.aryan.model.entity.User;
import com.aryan.repository.UserRepo;
import com.aryan.request.LoginRequest;
import com.aryan.response.AuthResponse;
import com.aryan.service.Implementation.CustomUserDetailsImpl;
import com.aryan.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isUserExist = userRepo.findByEmail(user.getEmail());
        if (isUserExist != null) {
            throw new Exception("Email already exists with another Account.");
        }

        User createdUser = userRepo.save(user);
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());

        User savedUser = userRepo.save(createdUser);

        subscriptionService.createSubscription(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setMessage("Signup Successful!");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
        String username= loginRequest.getEmail();
        String password= loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setMessage("Sign-In Successful!");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username.");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
