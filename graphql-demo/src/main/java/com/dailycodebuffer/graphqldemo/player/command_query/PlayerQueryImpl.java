package com.dailycodebuffer.graphqldemo.player.command_query;

import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.player.resolver.PlayerQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerQueryImpl implements PlayerQuery {

    private final PlayerQueryResolver playerQueryResolver;

    public PlayerQueryImpl(PlayerQueryResolver playerQueryResolver) {
        this.playerQueryResolver = playerQueryResolver;
    }

    public List<Player> findAllPLayers() {
        return playerQueryResolver.findAll();
    }

    public Player findByPlayerId(String playerId) {
        return playerQueryResolver.findOne(playerId);
    }
}
