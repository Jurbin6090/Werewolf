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
    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable(value="id") String id){
        return gameRepository.findOne(id);
    }

    @PostMapping("/game/create")
    public Game createGame(){
        Game game = new Game();
        game.setPlayers(new ArrayList<>());
        return gameRepository.save(game);
    }

    @DeleteMapping("/game/{id}")
    public String endGame(@PathVariable(value="id") String id){
        gameRepository.delete(id);
        return "Game over";
    }

    @PostMapping("/game/{gameId}/addPlayer/{playerId}")
    public Game addPlayer(@PathVariable(value="gameId") String gameId, @PathVariable(value="playerId") String playerId){
        Game game = gameRepository.findOne(gameId);
        game.getPlayers().add(playerRepository.findOne(playerId));
        return gameRepository.save(game);
    }

    @PostMapping("/game/drawCharacters/{gameId}")
    public String drawCharacters(@PathVariable(value="gameId") String gameId){
        Game game = gameRepository.findOne(gameId);
        List<Character> characters = characterRepository.findAll();
        if(game.getPlayers().size() == characters.size()) {
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
