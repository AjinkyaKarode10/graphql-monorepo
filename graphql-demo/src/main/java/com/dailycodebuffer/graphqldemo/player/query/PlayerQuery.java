package com.dailycodebuffer.graphqldemo.player.query;

import com.dailycodebuffer.graphqldemo.player.model.Player;

import java.util.List;

public interface PlayerQuery {

    public List<Player> findAllPLayers();
    public Player findByPlayerId(String playerId);
}
