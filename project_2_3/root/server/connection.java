package root.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class connection {
    static Socket socket;
    static PrintWriter writer;
    private static LinkedBlockingQueue<String> commandQueue;
    private static LinkedBlockingQueue<String> receiveQueue;
    private final String firstServerResponse = "Strategic Game Server Fixed [Version 1.1.0]\n" +
            "(C) Copyright 2015 Hanzehogeschool Groningen";
    //PrintWriter is voor output
    //BufferedReader is voor input

    public connection() {
        commandQueue = new LinkedBlockingQueue<>();
    }

    public void connectToServer(String ip, int port) {
        System.out.println("ConnectToServer Werkt begin");
        System.out.println("Logging into server: " + ip + " on port: " + port );
        try {
            socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //executorService.execute(new connection.ReadLines());
        executorService.execute(new Converstation());
        // executorService.shutdown();
    }

    public void login(String name) {
        System.out.println("-------------Logging in-------------");
        commandQueue.add("login " + name);
        System.out.println("Logged in as: " + name);
        printQueue();
        popQueue();
        printQueue();
    }

    public void logout() {
        commandQueue.add("logout");
    }

    public void getPlayerlist() {
        commandQueue.add("get playerlist");
    }

    public void getGamelist() {
        commandQueue.add("get gamelist");
    }

    public void acceptGameChallenge(int gameID) {
        commandQueue.add("challenge accept" + gameID);
    }

    public void challengePlayer(String opponent, String gamemode) {
        commandQueue.add("challenge " + opponent + gamemode);
    }

    public void setMove(int position) {
        commandQueue.add("move " + position);
    }

    public void forfeit() {
        commandQueue.add("forfeit");
    }

    public void getHelp() {
        commandQueue.add("help");
    }

    public void printQueue(){
         System.out.println(commandQueue);
    }
    public void popQueue(){
        Object test = commandQueue.peek();
        commandQueue.remove(test);
    }

    public static String getFirstItemCommandQueue(){
        String returner =  commandQueue.peek();
        System.out.println(returner);
        return returner;
    }

}

 class Converstation implements Runnable {

     @Override
      public void run() {
         sendStringToServer(connection.writer , connection.getFirstItemCommandQueue());
         commandQueue = new LinkedBlockingQueue<>();


      }
      public void sendStringToServer(PrintWriter writer, String command) {
         System.out.println("Sending command :" + command);
         System.out.println("writer " + writer);
         writer.println(command);
     }
 }



