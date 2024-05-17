package com.userservice.app.controller;

import com.userservice.app.dto.CreateUserDTO;
import com.userservice.app.dto.UpdateUserDTO;
import com.userservice.app.exception.CustomException;
import com.userservice.app.model.User;
import com.userservice.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${user.path}")
@RequiredArgsConstructor
public class UserController extends AbstractUserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    protected ResponseEntity<Page<User>> getAllUsers (Pageable pageable){
            Page<User> users = userService.getAllUsers(pageable);
            return sendSuccessResponse(users);
    }

    @GetMapping("/{id}")
    protected ResponseEntity<User> getUserById (@PathVariable String id){
        User user = userService.getUserById(id);
        return sendSuccessResponse(user);
    }

    @PostMapping
    protected ResponseEntity <User> createUser (@Valid @RequestBody CreateUserDTO createUserDTO) {
            User user = modelMapper.map(createUserDTO,User.class);
            User newUser = userService.createUser(user);
            return sendCreatedResponse(newUser);
    }

    @PutMapping("/{id}")
    protected ResponseEntity<User> updateUser (
            @PathVariable String id,
            @Valid @RequestBody UpdateUserDTO updateUserDTO
            ) {
        User user = modelMapper.map(updateUserDTO,User.class);
        User updatedUser = userService.updateUser(id,user);
        return sendUpdatedResponse(updatedUser);
    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<String> deleteUser (@PathVariable String id){
        userService.deleteUser(id);
        return sendNoContentResponse(id);
    }


}
