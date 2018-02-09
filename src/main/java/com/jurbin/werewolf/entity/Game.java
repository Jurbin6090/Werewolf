package com.jurbin.werewolf.entity;

import com.jurbin.werewolf.controller.CharacterController;
import com.jurbin.werewolf.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Game {
    @Autowired
    CharacterRepository characterRepository;

    @Id
    String id;
    ArrayList<Player> players;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
