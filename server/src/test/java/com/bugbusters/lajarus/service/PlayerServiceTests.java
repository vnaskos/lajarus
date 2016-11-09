package com.bugbusters.lajarus.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.model.Player;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlayerServiceTests {

	@Autowired
	PlayerService playerService;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void findPlayerByNameAdminTest() throws Exception {
		Player p = playerService.findPlayerByName("admin");
		assertNotNull(p);
		assertEquals(p.getName(), "admin");
	}

	@Test
	public void findPlayerByNameNullTest() throws Exception {
		exception.expect(Exception.class);
		playerService.findPlayerByName(null);
	}
	
	@Test
	public void findAllPlayersTest() throws Exception {
		List<PlayerEntity> players = playerService.getAll();
		assertEquals(players.size(), 2);
	}

}
