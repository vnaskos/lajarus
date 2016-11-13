package com.bugbusters.lajarus.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bugbusters.lajarus.security.entity.User;

/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
