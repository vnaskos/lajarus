package com.bugbusters.lajarus.model;

import com.bugbusters.lajarus.entity.PlayerEntity;

public class PlayerFactory {
	
	public PlayerFactory() {
		
	}

	public static Player create(PlayerEntity playerEntity) {
		return new Player(
				playerEntity.getName(),
				playerEntity.getLatitude(),
				playerEntity.getLongitude());
	}
}
