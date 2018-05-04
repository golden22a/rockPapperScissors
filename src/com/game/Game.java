package com.game;

public class Game {
    private int type;
    private String message;
    private Server gameServer;
    private boolean isServer = false;
    private boolean onSession = false;
    public Game(int type,String message){
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void getWinner(String a , String b){
        if(a.equals(b)){
            this.message =" It's a Tie";
        }
       else if (a.equals("paper") && b.equals("scissors") ){
            this.message = "You Lost!!!";
        }
        else if (a.equals("rock") && b.equals("paper")) {
            this.message = "You Lost!!!";
        }
        else if((a.equals("scissors")) && b.equals("rock")){
            this.message = "You Lost!!";
        }else {
            this.message = "You Won!!";
        }
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public void initServer(int port ){
        gameServer = new Server(port);
        isServer = true;
        onSession = true;

    }
    public String getLastMessage(){
        return this.gameServer.getMessage();
    }
    public void sendMessage(int choice){
        this.gameServer.sendMessage(choice);

    }

    public boolean isOnSession() {
        return onSession;
    }

    public void setOnSession(boolean onSession) {
        this.onSession = onSession;
    }
}
