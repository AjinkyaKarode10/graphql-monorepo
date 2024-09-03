package com.dailycodebuffer.graphqldemo.player.command_query;

import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.player.resolver.PlayerMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class PlayerCommandImpl implements PlayerCommand {

    private final PlayerMutationResolver playerMutationResolver;

    public PlayerCommandImpl(PlayerMutationResolver playerMutationResolver) {
        this.playerMutationResolver = playerMutationResolver;
    }

    public Player createPlayer(String playerId, String name, Team team) {
        Player player = new Player(playerId, name, team);
        return playerMutationResolver.create(player);
    }

    public Player updatePlayer(String playerId, String name, Team team) {
        Player player = new Player(playerId, name, team);
        return playerMutationResolver.update(player);
    }
}
