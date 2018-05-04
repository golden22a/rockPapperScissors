package com.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket ;
    private BufferedReader in  =  null;
    private BufferedWriter out =  null;
    private Boolean connected;


    public Client(int port){
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Connected");
            connected = true;
        }
        catch(IOException e){
            connected = false;
            this.socket=null;
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public void setOut(BufferedWriter out) {
        this.out = out;
    }
    public boolean isConnected(){
        return this.connected;
    }
}
