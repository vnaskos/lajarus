package com.bugbusters.lajarus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.model.Player;
import com.bugbusters.lajarus.model.PlayerFactory;
import com.bugbusters.lajarus.repository.PlayerRepository;

@Service
public class PlayerService {
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	public List<PlayerEntity> getAll() {
		return playerRepository.findAll();
	}
	
	public Player findPlayerByName(String name) throws Exception {
		 PlayerEntity playerEntity = playerRepository.findPlayerByName(name);
		 
		 if(playerEntity == null) {
			 throw new Exception("User " + name + " does not exist");
		 }
		 
		 return PlayerFactory.create(playerEntity);
	}
}
