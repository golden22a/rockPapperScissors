package com.game;

import processing.core.PImage;

// class for the item rock,paper,scissors containing image and name of action
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


    public String getName() {
        return name;
    }

    public static PImage getPimage(Item item){
       return item.getAction();
    }
    public static String toString(Item item){
        return item.getName();
    }
}
