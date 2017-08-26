package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Vasilis Naskos
 */
public class JwtUserDetailsServiceImplTest extends AbstractTest {
    
    @Autowired
    JwtUserDetailsServiceImpl service;
    
    @Test
    public void findAdminByUsername() {
        UserDetails user = service.loadUserByUsername("admin");
        
        Assert.assertNotNull("failed - expected not null", user);
        Assert.assertEquals("failed - expected admin", "admin", user.getUsername());
    }
    
    @Test
    public void searchForInvalidUserByUsername() {
        UserDetails user = service.loadUserByUsername("asdfg");
        
        Assert.assertNull("failed - expected null", user);
    }
    
}
