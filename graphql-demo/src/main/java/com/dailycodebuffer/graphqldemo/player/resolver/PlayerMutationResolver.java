package com.dailycodebuffer.graphqldemo.player.resolver;

import com.dailycodebuffer.graphqldemo.player.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import com.dailycodebuffer.graphqldemo.player.repository.PlayerRepository;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    private void init() {
        if (playerRepository.count() == 0) {
            playerRepository.save((new Player("100", "MS Dhoni", Team.CSK)));
            playerRepository.save((new Player("101", "Rohit Sharma", Team.MI)));
            playerRepository.save((new Player("102", "Jasprit Bumrah", Team.MI)));
            playerRepository.save((new Player("103", "Rishabh pant", Team.DC)));
            playerRepository.save((new Player("104", "Suresh Raina", Team.CSK)));
        }
    }

}
