package com.aman.springsecdemo.controller;

import com.aman.springsecdemo.model.User;
import com.aman.springsecdemo.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<Object> getAllUsers(){

        List<User> allUser = userService.getAllUser();

        if(allUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }
    @PostMapping("/user-role")
    public void changeRole(@RequestParam String role,@RequestParam String username){

        userService.updateRoles(role,username);


    }
}
