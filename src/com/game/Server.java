package com.game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket   = null;
    private ServerSocket server   = null;
    private BufferedReader in       =  null;
    private DataOutputStream out =  null;
    public Server(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("server started");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new BufferedReader((new InputStreamReader(socket.getInputStream())));

            String line = in.readLine();
            System.out.println(line);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
