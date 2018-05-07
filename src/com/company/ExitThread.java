package com.company;

import com.game.FileHundler;
// thread to hundle file reset after program shut down
public class ExitThread extends Thread {
    private String line;
    public ExitThread(String line){
        super("ExitThread");
        this.line = line;

    }
    public void run(){
        FileHundler.unset(line);
    }

}
