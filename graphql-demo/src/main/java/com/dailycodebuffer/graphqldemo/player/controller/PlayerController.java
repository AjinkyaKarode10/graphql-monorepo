package com.dailycodebuffer.graphqldemo.player.controller;

import com.dailycodebuffer.graphqldemo.player.command.PlayerCommandServiceImpl;
import com.dailycodebuffer.graphqldemo.player.query.PlayerQueryServiceImpl;
import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.player.resolver.PlayerSubscriptionResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class PlayerController {

//    private final PlayerService playerService;
//
//    public PlayerController(PlayerService playerService) {
//        this.playerService = playerService;
//    }

    private final PlayerCommandServiceImpl playerCommandServiceImpl;
    private final PlayerQueryServiceImpl playerQueryServiceImpl;
    private final PlayerSubscriptionResolver playerSubscriptionResolver;

    public PlayerController(PlayerCommandServiceImpl playerCommandServiceImpl, PlayerQueryServiceImpl playerQueryServiceImpl,PlayerSubscriptionResolver playerSubscriptionResolver) {
        this.playerCommandServiceImpl = playerCommandServiceImpl;
        this.playerQueryServiceImpl = playerQueryServiceImpl;
        this.playerSubscriptionResolver=playerSubscriptionResolver;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerQueryServiceImpl.findAllPLayers();
    }

    @QueryMapping
    public Player findOne(@Argument String playerId) {
        return playerQueryServiceImpl.findByPlayerId(playerId);
    }

    @MutationMapping
    public Player create(@Argument String playerId, @Argument String name, @Argument Team team) {
        return playerCommandServiceImpl.createPlayer(playerId,name,team);
    }

    @MutationMapping
    public Player update(@Argument String playerId, @Argument String name, @Argument Team team) {
        return playerCommandServiceImpl.updatePlayer(playerId,name,team);
    }

//    @MutationMapping
//    public Player delete(@Argument Integer id) {
//        return playerService.delete(id);
//    }

    @SubscriptionMapping
    public Flux<Player> playerCreated() {
        return playerSubscriptionResolver.playerCreated();
    }
}
