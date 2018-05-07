package com.game;

import com.player.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {
    private int type;
    private String message;
    private Server gameServer;
    private boolean isServer = false;
    private boolean onSession = false;
    private Player player = null;
    private String player2 = null;
    public Game(int type,String message,Player player){
        this.type = type;
        this.message = message;
        this.player=player;
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
            this.message = "You Lost!!!";
        }else {
            this.message = "You Won!!!";
        }

            this.saveToHistory(this.message);

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
        String str = this.gameServer.getMessage();
        String[] parsed =str.split("\\s+");
        this.player2 = parsed[0];
        return parsed[1];
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
