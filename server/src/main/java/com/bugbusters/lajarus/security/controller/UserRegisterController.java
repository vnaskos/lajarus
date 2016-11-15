/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.lajarus.security.controller;

import com.bugbusters.lajarus.security.entity.User;
import com.bugbusters.lajarus.security.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vasilis Naskos
 */
@RestController
@RequestMapping("register")
public class UserRegisterController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(HttpServletRequest request,
            @RequestBody User user) {
        
        userService.addUser(user);
        
        return ResponseEntity.ok(user.getFirstname());
    }
}
