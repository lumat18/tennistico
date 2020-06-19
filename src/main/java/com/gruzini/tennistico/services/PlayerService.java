package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getByUsername(final String username) {
        return playerRepository.findByUsername(username).orElseThrow(PlayerNotFoundException::new);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }
}