package com.bugbusters.lajarus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bugbusters.lajarus.security.entity.User;
import com.bugbusters.lajarus.security.JwtUserFactory;
import com.bugbusters.lajarus.repository.UserRepository;
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
    
    public void addNewUser(User userForm) {
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userRepository.saveAndFlush(userForm);
    }
}
