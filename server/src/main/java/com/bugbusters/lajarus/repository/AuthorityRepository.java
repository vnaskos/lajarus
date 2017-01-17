package com.bugbusters.lajarus.repository;

import com.bugbusters.lajarus.security.entity.Authority;
import com.bugbusters.lajarus.security.entity.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Vasilis Naskos
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    
    Authority findByName(AuthorityName name);
    
}
