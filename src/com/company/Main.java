package com.company;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends processing.core.PApplet{

    PImage computerImg;
    PImage multiImg;



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
        fill(0);
        textSize(40);
    }
    public void draw(){
        image(computerImg,300   ,200, 200, 200);
        image(multiImg,720,200, 200, 200);
        text("play vs Computer", 250, 450);
        text("play vs Player", 680, 450);
    }

}
