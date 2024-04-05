package com.example.projectwebbackend.controllers;

import com.example.projectwebbackend.dto.UserBookTicketRequest;
import com.example.projectwebbackend.dto.UserCreationRequest;
import com.example.projectwebbackend.dto.UserSigninRequest;
import com.example.projectwebbackend.dto.UserUpdateRequest;
import com.example.projectwebbackend.entity.Province;
import com.example.projectwebbackend.entity.Trip;
import com.example.projectwebbackend.entity.User;
import com.example.projectwebbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/dangky")
    User createUser(@RequestBody @Valid UserCreationRequest request) {

        return userService.createUser(request);
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<User> signinUser(@RequestBody @Valid UserSigninRequest request){
        return userService.signinUser(request.getAccount(), request.getPassword());
    }

    @PutMapping("/thaydoimatkhau")
    public ResponseEntity<User> updatePassword(@RequestParam String account, @RequestParam String newpassword) {
        return userService.updatePassword(account, newpassword);
    }

    @GetMapping("/danhsachtatcachuyenxe")
    public List<Trip> tripList() {
        return userService.tripList();
    }

    @GetMapping("/danhsachchuyenxetheodiemdiden")
    public List<Trip> tripfilterLocation(@RequestParam String startpname, @RequestParam String endpname) {
        return userService.tripfilterLocation(startpname, endpname);
    }

    //@PostMapping("/datvexe/{id}")
    //public ResponseEntity<String> bookTicket(@PathVariable Long id, @RequestBody UserBookTicketRequest request){
        //eturn userService.bookTicket(id, request);
    //}

    @GetMapping("/taikhoannguoidung")
    public  List<User> getUsers(){
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
