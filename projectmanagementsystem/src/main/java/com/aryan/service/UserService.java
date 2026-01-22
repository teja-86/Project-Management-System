package com.aryan.service;

import com.aryan.model.entity.User;

public interface UserService {
    User findUserByJwt(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long userId) throws Exception;

    User updateUsersProjectSize(User user, int number) throws Exception;

}
