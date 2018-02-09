package com.jurbin.werewolf.controller;

import com.jurbin.werewolf.entity.Player;
import com.jurbin.werewolf.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/player/list")
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable(value = "id") String id) {
        return playerRepository.findOne(id);
    }

    @PostMapping("/player/create")
    public Player createPlayer(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        Player player = new Player();

        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setWins(0);
        player.setLosses(0);

        return playerRepository.save(player);
    }

    @DeleteMapping("/player/{id}")
    public String removePlayer(@PathVariable(value = "id") String id) {
        playerRepository.delete(id);

        return "Player removed";
    }
}
