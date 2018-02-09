package com.jurbin.werewolf.entity;

import org.springframework.data.annotation.Id;

public class Character {

    @Id
    String id;
    String name;
    String description;

    Integer powerLevel;

    Boolean isVillager;

    NightPhase nightPhase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(Integer powerLevel) {
        this.powerLevel = powerLevel;
    }

    public Boolean getVillager() {
        return isVillager;
    }

    public void setVillager(Boolean villager) {
        isVillager = villager;
    }

    public NightPhase getNightPhase() {
        return nightPhase;
    }

    public void setNightPhase(NightPhase nightPhase) {
        this.nightPhase = nightPhase;
    }
}
