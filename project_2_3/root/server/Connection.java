package root.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Connection {
    static Socket socket;
    static PrintWriter writer;
    private static LinkedBlockingQueue<String> commandQueue;

    //PrintWriter is voor output
    //BufferedReader is voor input

    public Connection() {
        commandQueue = new LinkedBlockingQueue<>();
    }

    public void connectToServer(String ip, int port) {
        //System.out.println("ConnectToServer Werkt begin");
        System.out.println("Logging into server: " + ip + " on port: " + port);
        try {
            socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new ReadLines());
        executorService.execute(new Conversation());
        // executorService.shutdown();
    }

    public void login(String name) {
        System.out.println("-------------Logging in-------------");
        commandQueue.add("login " + name);
        System.out.println("Logged in as: " + name);
        printQueue();
        popQueue();
        //printQueue();
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

    public static void acceptGameChallenge(int gameID) {
        commandQueue.add("challenge accept" + gameID);
    }

    public void challengePlayer(String opponent, String gamemode) {
        commandQueue.add("challenge " + opponent + gamemode);
    }

    public void subscribe(String game){
        System.out.println("Subscribing to Reversi!");
        commandQueue.add("subscribe " + game);
        printQueue();
        popQueue();
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

    public static void printQueue() {
        System.out.println("Printing commandQueue: " + commandQueue);
    }

    public static void popQueue() {
        String test = commandQueue.peek();
        commandQueue.remove(test);
        printQueue();
    }

    public static String getFirstItemCommandQueue() {
        String returner = commandQueue.peek();
        System.out.println(returner);
        return returner;
    }


    static class Conversation implements Runnable {

        @Override
        public void run() {

                System.out.println("Conversation  is reached");
                sendStringToServer(Connection.writer, Connection.getFirstItemCommandQueue());




               // e.printStackTrace();

        }

        public void sendStringToServer(PrintWriter writer, String command) {
            System.out.println("Sending command :" + command);
            writer.println(command);
        }
    }

    static class ReadLines implements Runnable {
        BufferedReader reader;
        Interpreter interpreter = new Interpreter();

        private List<String> firstServerResponse = Arrays.asList("Strategic Game Server Fixed [Version 1.1.0]",
                "(C) Copyright 2015 Hanzehogeschool Groningen");

        @Override
        public void run() {
            buffReader();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    //System.out.println("Readlines: " +line);
                    if(line.equals(firstServerResponse)){
                        continue;
                    }
                    System.out.println("Server Response: " +line);
                    interpreter.inputInterpreter(line);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //initiating the buffered Reader
        void buffReader() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}



