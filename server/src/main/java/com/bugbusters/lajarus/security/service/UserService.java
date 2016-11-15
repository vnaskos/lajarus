package com.bugbusters.lajarus.security.service;

import com.bugbusters.lajarus.security.entity.User;
import com.bugbusters.lajarus.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
