package com.dailycodebuffer.graphqldemo.resolver;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.repository.PlayerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Optional;

@Component
public class PlayerMutationResolver {

    PlayerRepository playerRepository;
    private final Sinks.Many<Player> playerCreatedSink = Sinks.many().multicast().onBackpressureBuffer();

    public PlayerMutationResolver(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Flux<Player> playerCreated() {
        return playerCreatedSink.asFlux();
    }

    public Player create(Player player) {
        playerRepository.save(player);
        playerCreatedSink.tryEmitNext(player);
        return player;
    }

    public Player update(Player player) {
        Player updatedPlayerRecord;
        Optional<Player> optional = playerRepository.findByPlayerId(player.getPlayerId());

        if (optional.isPresent()) {
            Player existingPlayer = optional.get();
            existingPlayer.setName(player.getName());
            existingPlayer.setTeam(player.getTeam());
            updatedPlayerRecord = playerRepository.save(existingPlayer);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayerRecord;
    }
}
