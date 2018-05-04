package com.player;

import com.game.Item;

public class Player {
    private String name;
    private String choice;

    public Player(String name){
        this.name = name;
        choice=null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getName() {
        return name;
    }
}
