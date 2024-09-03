package com.dailycodebuffer.graphqldemo.player.command;

import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;

public interface PlayerCommandService {
    public Player createPlayer(String playerId, String name, Team team);
    public Player updatePlayer(String playerId, String name, Team team);
}
