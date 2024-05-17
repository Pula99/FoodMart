package com.userservice.app.service.impl;

import com.userservice.app.exception.NotFound;
import com.userservice.app.model.User;
import com.userservice.app.repository.UserRepository;
import com.userservice.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        try {
            return userRepository.findAll(pageable);
        } catch (Exception exception) {
            log.error("Error occurred when getting all users", exception);
            throw exception;
        }
    }

    @Override
    public User getUserById(String id) {
        try {
            return userRepository.findById(id).orElseThrow(() -> new NotFound("Error occurred when getting user with id : %s", id));
        } catch (Exception exception) {
            log.error("Error occurred when getting user with id : {}", id, exception);
            throw exception;
        }
    }

    @Override
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception exception) {
            log.error("Error occurred when creating new user", exception);
            throw exception;
        }
    }

    @Override
    public User updateUser(String id, User updatedUser) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new NotFound("Error occurred when getting user with id : %s", id));
            modelMapper.map(updatedUser, user);
            return userRepository.save(user);
        } catch (Exception exception) {
            log.error("Error occurred when updating user with id : {}", id, exception);
            throw exception;
        }
    }

    @Override
    public void deleteUser(String id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error occurred when deleting user with id : {}", id, exception);
            throw exception;
        }
    }
}
