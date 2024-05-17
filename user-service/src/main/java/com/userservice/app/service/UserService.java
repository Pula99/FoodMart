package com.userservice.app.service;

import com.userservice.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Page<User> getAllUsers(Pageable pageable);
    User getUserById (String id);
    User createUser(User user);
    User updateUser(String id,User user);
    void deleteUser (String id);
}
