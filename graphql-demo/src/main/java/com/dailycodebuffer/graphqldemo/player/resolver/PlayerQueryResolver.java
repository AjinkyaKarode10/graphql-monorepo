package com.dailycodebuffer.graphqldemo.player.resolver;

import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.player.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlayerQueryResolver {

    PlayerRepository playerRepository;
    public PlayerQueryResolver(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findOne(String pId) {
        Optional<Player> playerOp = playerRepository.findByPlayerId(pId);
        return playerOp.orElse(null);
    }
}
