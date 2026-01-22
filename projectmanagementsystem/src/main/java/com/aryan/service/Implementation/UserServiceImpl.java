package com.aryan.service.Implementation;

import com.aryan.config.JwtProvider;
import com.aryan.model.entity.User;
import com.aryan.repository.UserRepo;
import com.aryan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        if (jwt == null || jwt.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT cannot be null or empty");
        }

        String email = JwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new Exception("User Not Found!");
        }

        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new Exception("User Not Found!");
        }

        return optionalUser.get();
    }

    @Override
    public User updateUsersProjectSize(User user, int number) throws Exception {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        user.setProjectSize(user.getProjectSize()+number);
        return userRepo.save(user);
    }

}
