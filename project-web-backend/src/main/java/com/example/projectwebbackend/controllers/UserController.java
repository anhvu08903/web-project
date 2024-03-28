package com.example.projectwebbackend.controllers;

import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.dto.UserUpdateRequest;
import com.example.projectwebbackend.entity.User;
import com.example.projectwebbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    User createUser(@RequestBody UserCreationRequest request) {

        return userService.createUser(request);
    }

    @GetMapping("")
    List<User> getUsers(){
         return userService.getUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable long id, @RequestBody UserUpdateRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User has been deleted";
    }
}
