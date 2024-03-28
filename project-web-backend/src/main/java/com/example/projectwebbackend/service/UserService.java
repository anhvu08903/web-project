package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.dto.UserUpdateRequest;
import com.example.projectwebbackend.entity.User;
import com.example.projectwebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setAccount(request.getAccount());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());

        return userRepository.save(user);

    }
     public List<User> getUsers(){
        return userRepository.findAll();
     }

     public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
     }

     public User updateUser(Long id, UserUpdateRequest request){
        User user = getUser(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
     }

     public void deleteUser(Long id){
        userRepository.deleteById(id);
     }
}
