package com.player;

import java.io.*;
import java.net.Socket;
// client socker
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


    public boolean isConnected(){
        return this.connected;
    }
    public void sendMessage(String choice){
        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(choice);
            out.newLine();
            out.flush();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public String getMessage(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return in.readLine();
        }
        catch(IOException e){
            System.out.println(e);
            return "No message fetched";
        }
    }


}
