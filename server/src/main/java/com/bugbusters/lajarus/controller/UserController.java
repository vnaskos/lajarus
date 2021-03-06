package com.bugbusters.lajarus.controller;

import com.bugbusters.lajarus.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.security.JwtTokenUtil;
import com.bugbusters.lajarus.security.JwtUser;
import com.bugbusters.lajarus.service.JwtUserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for user management,
 * get user by the authentication token
 * 
 * @author vasilis
 */
@RestController
@RequestMapping(value = "user", method = RequestMethod.GET)
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userService;

    /**
     * Get user from the provided auth token
     * 
     * @param request
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userService.loadUserByUsername(username);
        return user;
    }
    
//    @RequestMapping(value = "players", method = RequestMethod.GET)
//    public List<PlayerEntity> getUserPlayers(HttpServletRequest request) {
//        String token = request.getHeader(tokenHeader);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        User user = userService.findUserByUsername(username);
//        user.
//    }
}
