package com.bugbusters.lajarus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.security.JwtTokenUtil;
import com.bugbusters.lajarus.security.JwtUser;
import com.bugbusters.lajarus.security.entity.User;
import com.bugbusters.lajarus.service.JwtUserDetailsServiceImpl;
import com.bugbusters.lajarus.validation.ValidationErrorBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "user", method = RequestMethod.GET)
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userService.loadUserByUsername(username);
        return user;
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(HttpServletRequest request,
            @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        userService.addUser(user);

        return ResponseEntity.ok(user.getFirstname());
    }

}
