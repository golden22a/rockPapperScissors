package com.player;

// player class
public class Player {
    private String name;
    private String choice;
    private Client playerClient = null;
    private Boolean session = false;
    public Player(String name){
        this.name = name;
        choice=null;
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
    // check if connecton was etablished
   public boolean isConnected(){

       return playerClient.isConnected();
   }
   public void sendMessage(int choice){
        this.playerClient.sendMessage(this.getName()+"  "+choice);
   }
   public String getMessage(){

      String message =  this.playerClient.getMessage();
       this.session=true;
       return message;
   }

}
