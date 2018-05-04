package com.player;

import com.game.Item;

import java.net.Socket;

public class Player {
    private String name;
    private String choice;
    private Client playerClient = null;
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
    public void initSocket(){
        playerClient = new Client(5000);

    }

   public boolean isConnected(){
       return playerClient.isConnected();
   }
}
