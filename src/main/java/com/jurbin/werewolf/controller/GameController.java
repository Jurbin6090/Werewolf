package com.jurbin.werewolf.controller;

import com.jurbin.werewolf.entity.Character;
import com.jurbin.werewolf.entity.Game;
import com.jurbin.werewolf.entity.Player;
import com.jurbin.werewolf.repository.CharacterRepository;
import com.jurbin.werewolf.repository.GameRepository;
import com.jurbin.werewolf.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CharacterRepository characterRepository;

    @GetMapping("/game/list")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable(value = "id") String id) {
        return gameRepository.findOne(id);
    }

    @PostMapping("/game/create/{playerId}")
    public Game createGame(@PathVariable(value = "playerId") String playerId) {
        Player player = playerRepository.findOne(playerId);
        Game game = new Game();

        game.setName(player.getFirstName() + " " + player.getLastName() + "'s game");
        game.setPlayers(new ArrayList<>());
        game.getPlayers().add(player);
        game.setCharacters(new ArrayList<>());

        return gameRepository.save(game);
    }

    @DeleteMapping("/game/{id}")
    public String endGame(@PathVariable(value = "id") String id) {
        gameRepository.delete(id);

        return "Game over";
    }

    @PostMapping("/game/{gameId}/addPlayer/{playerId}")
    public Game addPlayer(@PathVariable(value = "gameId") String gameId, @PathVariable(value = "playerId") String playerId) {
        Game game = gameRepository.findOne(gameId);

        game.getPlayers().add(playerRepository.findOne(playerId));

        return gameRepository.save(game);
    }

    @PostMapping("/game/{gameId}/addCharacter/{characterId}")
    public Game addCharacter(@PathVariable(value = "gameId") String gameId, @PathVariable(value = "characterId") String characterId) {
        Game game = gameRepository.findOne(gameId);

        game.getCharacters().add(characterRepository.findOne(characterId));

        return gameRepository.save(game);
    }

    @DeleteMapping("/game/{gameId}/removeCharacter")
    public String removeCharacter(@PathVariable(value = "gameId") String gameId, @RequestParam String name) {
        Game game = gameRepository.findOne(gameId);

        for (Character character : game.getCharacters()) {
            if (character.getName() == name) {
                game.getCharacters().remove(character);
                break;
            }
        }

        return "Character removed.";
    }

    @PostMapping("/game/drawCharacters/{gameId}")
    public String drawCharacters(@PathVariable(value = "gameId") String gameId) {
        Game game = gameRepository.findOne(gameId);
        List<Character> characters = game.getCharacters();

        if (game.getPlayers().size() == characters.size()) {
            game.getPlayers().forEach((player -> {
                Integer random = (int) Math.floor(Math.random() * characters.size());
                Character randomCharacter = characters.get(random);

                player.setCharacter(randomCharacter);
                characters.remove(randomCharacter);
            }));

            gameRepository.save(game);

            return "Characters assigned";
        }

        return "Must have same number of characters as players";
    }
}
