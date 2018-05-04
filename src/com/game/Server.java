package com.game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket   = null;
    private ServerSocket server   = null;
    private BufferedReader in       =  null;
    private BufferedWriter out =  null;
    private String lastMessage = "";
    public Server(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("server started");
            System.out.println("Waiting for player to join");
            socket = server.accept();
            System.out.println("Client accepted");

        }
        catch(IOException e){
            System.out.println(e);
        }
    }
   public String getMessage(){
        try {
            System.out.println("waiting for choice");
            in = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            lastMessage = in.readLine();
            System.out.println(lastMessage);
            return lastMessage;
        }catch(IOException e){
            System.out.println(e);
            return "ERROR";
        }
   }
    public void sendMessage(int choice){
        try {

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(""+choice);
            out.newLine();
            out.flush();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
