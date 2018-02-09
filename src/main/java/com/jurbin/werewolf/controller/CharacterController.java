package com.jurbin.werewolf.controller;

import com.jurbin.werewolf.entity.Character;
import com.jurbin.werewolf.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CharacterController {
    @Autowired
    CharacterRepository characterRepository;

    @GetMapping(value = "/character/list")
    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    @GetMapping(value = "/character/{id}")
    public Character getCharacter(@PathVariable(value = "id") String id) {
        return characterRepository.findOne(id);
    }

    @PostMapping(value = "/character/create")
    public Character createCharacter(@RequestParam String name, @RequestParam String description, @RequestParam Integer powerLevel, @RequestParam Boolean isVillager) {
        Character character = new Character();

        character.setName(name);
        character.setDescription(description);
        character.setPowerLevel(powerLevel);
        character.setVillager(isVillager);

        return characterRepository.save(character);
    }

    @DeleteMapping(value = "/character/remove/{id}")
    public String removeCharacter(@PathVariable(value = "id") String id) {
        characterRepository.delete(id);

        return "Character removed";
    }
}
