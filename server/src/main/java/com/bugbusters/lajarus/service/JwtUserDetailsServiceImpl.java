package com.bugbusters.lajarus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bugbusters.lajarus.security.entity.User;
import com.bugbusters.lajarus.security.JwtUserFactory;
import com.bugbusters.lajarus.repository.UserRepository;
import java.util.Date;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            return null;
        }
        
        return JwtUserFactory.create(user);
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public void addNewUser(User userForm) {
        if(userForm.getEnabled() == null) {
            userForm.setEnabled(Boolean.TRUE);
        }
        if(userForm.getFirstname()== null) {
            userForm.setFirstname("");
        }
        if(userForm.getLastname() == null) {
            userForm.setLastname("");
        }
        if(userForm.getLastPasswordResetDate() == null) {
            userForm.setLastPasswordResetDate(new Date());
        }
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userRepository.saveAndFlush(userForm);
    }
}
