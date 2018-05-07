package com.game;

import com.player.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {
    // type of game 0 for vs computer and 1 for multiplayer
    private int type;
    // message or status of the game
    private String message;
    // server socket
    private Server gameServer;
    // to check if the actual game is the host or is on a session with another host
    private boolean isServer = false;
    // to check if there is a session going (messsage sent , waiting for response)_
    private boolean onSession = false;
    // the actual player that created the game
    private Player player = null;
    // name of the second player incase of multi
    private String player2 = null;

    public Game(int type,String message,Player player){
        this.type = type;
        this.message = message;
        this.player=player;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
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
            this.message = "You Lost!!!";
        }else {
            this.message = "You Won!!!";
        }
            // register to history after getting the winner
            this.saveToHistory(this.message);

    }

    public boolean isServer() {
        return isServer;
    }

    // creating the server
    public void initServer(int port ){
        gameServer = new Server(port);
        isServer = true;
        onSession = true;

    }
    // getting last message from the client socket
    public String getLastMessage(){
        String str = this.gameServer.getMessage();
        String[] parsed =str.split("\\s+");
        this.player2 = parsed[0];
        return parsed[1];
    }
    // send message to the client
    public void sendMessage(int choice){
        this.gameServer.sendMessage(choice);

    }

    public boolean isOnSession() {
        return onSession;
    }

    public void setOnSession(boolean onSession) {
        this.onSession = onSession;
    }
    // save to history.txt
    public void saveToHistory(String line){
        String str;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String strDate=dateFormat.format(date);
        if(this.type == 0 ){
           str = player.getName()+"     "+"Computer     "+line+"    "+strDate ;
            FileHundler.addHistory(str);
        }
        else if(this.type == 1 && this.isServer){
            if(line.equals("You Won!!!")){
                str = player.getName()+"     "+player2+"     "+player.getName()+"    "+strDate ;

            }else if(line.equals("You Lost!!!")){
                str = player.getName()+"     "+player2+"     "+player2+"    "+strDate ;

            }
            else {
                str = player.getName()+"     "+player2+"     "+line+"    "+strDate ;

            }
            FileHundler.addHistory(str);
        }
    }
}
