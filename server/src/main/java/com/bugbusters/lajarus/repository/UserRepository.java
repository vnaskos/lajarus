package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bugbusters.lajarus.security.entity.User;

/**
 * Repository for user management
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
