package com.dailycodebuffer.graphqldemo.player.resolver;


import com.dailycodebuffer.graphqldemo.player.model.Player;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class PlayerSubscriptionResolver {
    PlayerMutationResolver playerMutationResolver;
    public PlayerSubscriptionResolver(PlayerMutationResolver playerMutationResolver) {
        this.playerMutationResolver = playerMutationResolver;
    }

    public Flux<Player> playerCreated() {
        return playerMutationResolver.playerCreated();
    }

}

