package com.example.projectwebbackend.service;

import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.dto.UserUpdateRequest;
import com.example.projectwebbackend.entity.User;
import com.example.projectwebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();
        if (userRepository.existsByAccount(request.getAccount())){
            throw  new RuntimeException("User existed.");
        }
        if (userRepository.existsByPhone(request.getPhone())){
            throw new RuntimeException("Phone number existed.");
        }
        user.setName(request.getName());
        user.setAccount(request.getAccount());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());

        return userRepository.save(user);

    }
    public ResponseEntity<User> signinUser(String account, String password){
        User user =  userRepository.findByAccount(account);
        if(user == null) {
            throw  new RuntimeException("User's not existed.");
        }
        if(!user.getPassword().equals(password)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updatePassword(String account, String newpassword){
        User user = userRepository.findByAccount(account);
        user.setPassword(newpassword);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
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
