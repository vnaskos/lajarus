package com.bugbusters.lajarus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bugbusters.lajarus.security.JwtAuthenticationRequest;
import com.bugbusters.lajarus.security.JwtTokenUtil;
import com.bugbusters.lajarus.security.JwtUser;
import com.bugbusters.lajarus.security.JwtAuthenticationResponse;
import com.bugbusters.lajarus.security.entity.User;
import com.bugbusters.lajarus.service.JwtUserDetailsServiceImpl;
import com.bugbusters.lajarus.validation.UserValidator;
import com.bugbusters.lajarus.validation.ValidationErrorBuilder;

import javax.servlet.http.HttpServletRequest;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.validation.Errors;

/**
 * Controller for authentication processes
 * create token - login, refresh token, register user
 * 
 * @author Vasilis Naskos
 */
@RestController
@RequestMapping(value = "/auth")
@Api(name = "User authentication API",
        description = "Provides methods for user login and register",
        stage = ApiStage.RC)
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private UserValidator userValidator;

    /**
     * Create new authentication key with the provided credentials
     * 
     * @param authenticationRequest Username and password as JSON
     * @param device
     * @return auth key as JSON
     * @throws AuthenticationException 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiMethod(description = "Create authentication token")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, Device device)
            throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    /**
     * Refresh an expired auth key
     * 
     * @param request
     * @return refreshed auth key as JSON
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    @ApiMethod(description = "Refresh an existing authentication token")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    /**
     * Register a new user by providing the appropriate info
     * 
     * @param request
     * @param userForm details about the new user (username, pass, ...)
     * @param errors errors after details check
     * @return username if process was successful
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiMethod(description = "Register a new user")
    public ResponseEntity<?> registerUser(HttpServletRequest request,
            @RequestBody User userForm, Errors errors) {
        userValidator.validate(userForm, errors);
        
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(ValidationErrorBuilder
                            .fromBindingErrors(errors));
        }
        
        userDetailsService.addNewUser(userForm);

        return ResponseEntity.ok(userForm.getUsername());
    }

}