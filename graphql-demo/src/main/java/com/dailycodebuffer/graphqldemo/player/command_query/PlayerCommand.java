package com.dailycodebuffer.graphqldemo.player.command_query;

import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;

public interface PlayerCommand {
    public Player createPlayer(String playerId, String name, Team team);
    public Player updatePlayer(String playerId, String name, Team team);
}
