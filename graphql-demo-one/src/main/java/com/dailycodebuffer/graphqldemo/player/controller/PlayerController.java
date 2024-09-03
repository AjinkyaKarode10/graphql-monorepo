package com.dailycodebuffer.graphqldemo.player.controller;

import com.dailycodebuffer.graphqldemo.player.command_query.PlayerCommandImpl;
import com.dailycodebuffer.graphqldemo.player.command_query.PlayerQueryImpl;
import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.player.resolver.PlayerSubscriptionResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class PlayerController {

//    private final PlayerService playerService;
//
//    public PlayerController(PlayerService playerService) {
//        this.playerService = playerService;
//    }

    private final PlayerCommandImpl playerCommandServiceImpl;
    private final PlayerQueryImpl playerQueryServiceImpl;
    private final PlayerSubscriptionResolver playerSubscriptionResolver;

    public PlayerController(PlayerCommandImpl playerCommandServiceImpl, PlayerQueryImpl playerQueryServiceImpl, PlayerSubscriptionResolver playerSubscriptionResolver) {
        this.playerCommandServiceImpl = playerCommandServiceImpl;
        this.playerQueryServiceImpl = playerQueryServiceImpl;
        this.playerSubscriptionResolver=playerSubscriptionResolver;
    }

    @QueryMapping
    public Flux<Player> findAll() {
        return Flux.fromIterable(playerQueryServiceImpl.findAllPLayers());
    }

    @QueryMapping
    public Mono<Player> findOne(@Argument String playerId) {
        return Mono.just(playerQueryServiceImpl.findByPlayerId(playerId));
    }

    @MutationMapping
    public Mono<Player> create(@Argument String playerId, @Argument String name, @Argument Team team) {
        return Mono.just(playerCommandServiceImpl.createPlayer(playerId,name,team));
    }

    @MutationMapping
    public Mono<Player> update(@Argument String playerId, @Argument String name, @Argument Team team) {
        return Mono.just(playerCommandServiceImpl.updatePlayer(playerId,name,team));
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
