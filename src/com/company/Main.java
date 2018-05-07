package com.company;

import com.game.FileHundler;
import com.game.Game;
import com.game.Item;
import com.player.Player;
import processing.core.PApplet;
import processing.core.PImage;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main extends processing.core.PApplet{
    int computerChoice;
    int time = 60;
    int itemsIndex = 0;
    int iteration = 0;
    int playerTwoChoice = -1;
    PImage computerImg;
    PImage multiImg;
    PImage historyImg;
    Item rockImg;
    Item paperImg;
    Item scissorsImg;
    Game game;
    ArrayList<Item> choices;
    boolean gameStarted = false;
    Player player;
    boolean playerChoised = false;
    boolean showHistory = false;
    PImage temp;
    String message;
    String[] userData;
    List<String> history;
    String historyToDisplay="";
    int historyIndex = 0;
    int pagination;



    public static void main(String[] args)   {


            PApplet.main("com.company.Main",args);
        }
    public void settings(){
        // window size
        size(1280, 720);

    }
    public void setup(){

        computerImg = loadImage("user.png");
        multiImg = loadImage("multiple.png");
        rockImg = new Item(loadImage("rock.png"),"rock");
        paperImg = new Item(loadImage("paper.png"),"paper");
        scissorsImg = new Item (loadImage("scissors.png"),"scissors");
        historyImg = loadImage("history.png");
        choices = new ArrayList();
        choices.add(rockImg);
        choices.add(paperImg);
        choices.add(scissorsImg);
        temp = rockImg.getAction();
        fill(0);
        textSize(40);
         String line = FileHundler.getUser();
         userData = line.split("\\s+");
        if(line.equals("not found") || line.equals("file not found") || line.equals("IO exception"))
            exit();
        player = new Player(userData[1]);
        ExitThread exitThread = new ExitThread(line);
        Runtime.getRuntime().addShutdownHook(exitThread);
    }
    public void draw(){
        background(255);
        text("Welcome "+player.getName(),400, 100);
        if(!gameStarted && !showHistory) {

            image(computerImg, 300, 200, 200, 200);
            image(multiImg, 720, 200, 200, 200);
            text("play vs Computer", 250, 450);
            text("play vs Player", 680, 450);
            image(historyImg, 1100, 10, 100, 100);

        }else if(!gameStarted && showHistory){
            background(255);
            text(historyToDisplay,100, 100);
        }
        else {
                        if(playerChoised && game.getType() == 0) {

                            if (time  % 5 == 0) {
                                temp = Item.getPimage(choices.get(itemsIndex));
                                itemsIndex++;
                                if (itemsIndex > 2) {
                                    itemsIndex = 0;
                                }
                            }
                          iteration++;
                            if(iteration > computerChoice-120){
                                playerChoised=false;
                                iteration = 0;
                                time = 60;
                                temp = Item.getPimage(choices.get(computerChoice - 200));
                                game.getWinner(player.getChoice(),Item.toString(choices.get(computerChoice - 200)));
                            }
                            time++;
                        }
                        else if(playerChoised ){
                            if(playerTwoChoice != -1){
                                temp = Item.getPimage(choices.get( playerTwoChoice));
                                game.getWinner(player.getChoice(),Item.toString(choices.get(playerTwoChoice)));
                                playerTwoChoice = -1;
                                playerChoised = false;
                                game.setOnSession(false);
                            }
                        }

            image(temp, 550, 100, 200, 200);
            image(Item.getPimage(choices.get(0)), 250, 350, 200, 200);
            image(Item.getPimage(choices.get(1)), 550, 350, 200, 200);
            image(Item.getPimage(choices.get(2)), 850, 350, 200, 200);
            text(game.getMessage(), 520, 640);
        }

    }
    public void mousePressed(){

        if(mouseX >= 300 && mouseX <=500 && mouseY >=200 && mouseY <=400 && !gameStarted){
            gameStarted=true;
            game=new Game(0,"Game Started!",player);
        }
        else if (mouseX >= 720 && mouseX <= 920 && mouseY >=200 && mouseY <=400 && !gameStarted ){
            game = new Game(1,"Game started for multiplayer session",player);
            gameStarted = true;
            player.initSocket();
            System.out.println(player.isConnected());
            if(!player.isConnected()){
                game.initServer(5000);
                playerTwoChoice = Integer.parseInt(game.getLastMessage());
            }
        }
        else if(mouseX >= 1100 && mouseX <= 1200 && mouseY >=10 && mouseY <=110){
            showHistory=true;
            history=FileHundler.getHistory(player.getName());
            if(history.size() % 8 == 0){
                pagination=history.size()/8-1;
            }else{
                pagination=history.size()/8;
            }
            for(int i=0;i<history.size();i++){
                if(i == 8){
                    break;
                }
                historyToDisplay += history.get(i)+"\n";

            }
        }
        else if(mouseX >= 250 && mouseX <=450 && mouseY >=350 && mouseY <=550 && gameStarted && !playerChoised){

            playerChoised = true;
            player.setChoice(Item.toString(choices.get(0)));
            System.out.println(game.isServer());
            if(game.getType() == 0) {
                computerChoice = (int) (Math.random() * 3 + 200);
                System.out.println(computerChoice);
            }
            else if (!game.isServer()){
                player.sendMessage(0);
                playerTwoChoice = Integer.parseInt(player.getMessage());
                System.out.println(playerTwoChoice);
            }else{
                game.sendMessage(0);
                if(!game.isOnSession())
                    playerTwoChoice = Integer.parseInt(game.getLastMessage());

            }

        }
        else if (mouseX >= 550 && mouseX <=750 && mouseY >=350 && mouseY <=550 && gameStarted && !playerChoised){

            playerChoised = true;
            player.setChoice(Item.toString(choices.get(1)));
            System.out.println(game.isServer());
            if(game.getType() == 0) {
                computerChoice = (int) (Math.random() * 3 + 200);
                System.out.println(computerChoice);
            }
            else if (!game.isServer()){
                player.sendMessage(1);
                playerTwoChoice = Integer.parseInt(player.getMessage());
                System.out.println(playerTwoChoice);
            }
            else {
                game.sendMessage(1);
                if(!game.isOnSession())
                    playerTwoChoice = Integer.parseInt(game.getLastMessage());


            }
        }
        else if(mouseX >= 850 && mouseX <=1050 && mouseY >=350 && mouseY <=550 && gameStarted && !playerChoised){
            playerChoised = true;
            player.setChoice(Item.toString(choices.get(2)));
            System.out.println(game.isServer());
            if(game.getType() == 0) {
                computerChoice = (int) (Math.random() * 3 + 200);
                System.out.println(computerChoice);
            }
            else if (!game.isServer()){
                player.sendMessage(2);
                playerTwoChoice = Integer.parseInt(player.getMessage());
                System.out.println(playerTwoChoice);
            }
            else{
                game.sendMessage(2);
                if(!game.isOnSession())
                    playerTwoChoice = Integer.parseInt(game.getLastMessage());

            }
        }


    }
    public void keyPressed(){
        System.out.println(keyCode);
        if(keyCode == 39){
            historyIndex++;
            historyToDisplay="";
        if(historyIndex > pagination){
            historyIndex=0;
        }
        for(int i=historyIndex*8;i<historyIndex*8+8;i++){
            if( i >= history.size()){
                break;
            }
            historyToDisplay += history.get(i)+"\n";
            }
        }else if(keyCode == 37){
            historyIndex--;
            historyToDisplay="";
            if(historyIndex < 0){
                historyIndex=pagination;
            }
            for(int i=historyIndex*8;i<historyIndex*8+8;i++){
                if( i >= history.size()){
                    break;
                }
                historyToDisplay += history.get(i)+"\n";
            }
        }

    }
}
