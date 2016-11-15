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
public class UserRegisterController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(
            value = "${jwt.route.authentication.register}",
            method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(HttpServletRequest request,
            @RequestBody User user) {
        
        userService.addUser(user);
        
        return ResponseEntity.ok(user.getFirstname());
    }
}
