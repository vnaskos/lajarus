package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.AbstractTest;
import com.bugbusters.lajarus.entity.PlayerEntity;
import java.util.Collection;
import javax.persistence.NoResultException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
@Transactional
public class PlayerServiceTest extends AbstractTest {
    
    @Autowired
    private PlayerService service;
    
    @Test
    public void findAllTest() {
        Collection<PlayerEntity> list = service.findAll();
        
        Assert.assertNotNull("failed - expected not null", list);
        Assert.assertEquals("failed - expected 4", 4, list.size());
    }
    
    @Test
    public void findPlayerAdminByNameTest() throws Exception {
        PlayerEntity player = service.findPlayerByName("admin");
        
        Assert.assertNotNull("failed - expected not null", player);
        Assert.assertEquals("failed - expected admin", "admin", player.getName());
    }
    
    @Test(expected = NoResultException.class)
    public void findInvalidPlayerByNameTest() {
        PlayerEntity player = service.findPlayerByName("asdfg");
        
        Assert.assertNull("failed - expected null", player);
    }
    
    @Test
    public void findPlayersNearToPlayerTest() throws Exception {
        Collection<PlayerEntity> list = service.getNearbyPlayers("admin");
        
        Assert.assertNotNull("failed - expected not null", list);
        Assert.assertEquals("failed - expected 2", 2, list.size());
    }
    
    @Test
    public void updatePlayerLocationTest() throws Exception {
        service.updatePlayerLocation("admin", "34.1234", "43.1234");
        
        PlayerEntity p = service.findPlayerByName("admin");
        
        Assert.assertEquals("failed - expected 34.1234", 
                34.1234, p.getLatitude(), 0.0001);
        Assert.assertEquals("failed - expected 43.1234",
                43.1234, p.getLongitude(), 0.0001);
    }
    
}
