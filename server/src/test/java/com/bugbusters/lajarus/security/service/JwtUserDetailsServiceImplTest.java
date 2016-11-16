package com.bugbusters.lajarus.security.service;

import com.bugbusters.lajarus.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
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
    
    @Test(expected = UsernameNotFoundException.class)
    public void searchForInvalidUserByUsername() {
        UserDetails user = service.loadUserByUsername("asdfg");
        
        Assert.assertNull("failed - expected null", user);
    }
    
}
