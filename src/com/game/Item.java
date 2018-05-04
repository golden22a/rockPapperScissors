package com.game;

import processing.core.PImage;

import java.util.ArrayList;

public class Item {
   private PImage action;
   private String name;
   public Item(PImage item,String name){
       this.name = name;
       action = item;
   }

    public PImage getAction() {
        return action;
    }

    public void setAction(PImage action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static PImage getPimage(Item item){
       return item.getAction();
    }
    public static String toString(Item item){
        return item.getName();
    }
}
