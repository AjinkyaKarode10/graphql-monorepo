package com.dailycodebuffer.graphqldemo.player.repository;

import com.dailycodebuffer.graphqldemo.player.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByPlayerId(String pId);
}
