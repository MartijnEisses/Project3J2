package root.server;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import root.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Connection {
    static Socket socket;
    static PrintWriter writer;
    private static BlockingQueue<String> commandQueue;
    private static List<String> playerList;
    private static List<String> challenges;
    //PrintWriter is voor output
    //BufferedReader is voor input

    public Connection() throws IOException {
        commandQueue = new LinkedBlockingQueue<>();
    }

    public void connectToServer(String ip, int port) {
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

    }

    public void login(String name) {
        System.out.println("-------------Logging in-------------");
        commandQueue.add("login " + name);
        System.out.println("Logged in as: " + name);
        printQueue();
        popQueue();
        //printQueue();
    }

    public static void logout() {
        commandQueue.add("logout");
    }

    public static List<String> getPlayerlist() {
        commandQueue.add("get playerlist");
        return playerList;
    }

    public static void getGamelist() {
        commandQueue.add("get gamelist");

    }

    public  void acceptGameChallenge(int gameID) {
        System.out.println("Start acceptGameChallenge, sending challenge accept!");
        commandQueue.add("challenge accept " + gameID);
        printQueue();
        popQueue();
    }

    public static void challengePlayer(String opponent, String gamemode) {
        commandQueue.add("challenge \"" + opponent + "\" \"" + gamemode + "\"");
    }

    public static void subscribe(String game){
        System.out.println("Subscribing to Reversi!");
        commandQueue.add("subscribe " + game);
        printQueue();
        popQueue();
        //printQueue();
    }

    public static void setMove(int position) {
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
        //printQueue();
    }

    public static BlockingQueue<String> getCommandQueue() {
        return commandQueue;
    }



    static class Conversation implements Runnable {
        boolean runner  = true;
        static BlockingQueue<String> queue = Connection.getCommandQueue();
        String command;
        @Override
        public void run() {
            while(runner) {
                try {
                    while ((command = queue.poll()) != null) {
                    //System.out.println("Conversation  is reached");
                    sendStringToServer(Connection.writer, command);
                     if(command.equals("logout")){runner = false;}
                    // e.printStackTrace();
                     }
                } catch (NullPointerException e) {
                    //e.printStackTrace();
                }
            }
        }

        public static void sendStringToServer(PrintWriter writer, String command) {

            writer.println(command);
            writer.flush();
        }
    }

    static class ReadLines implements Runnable {
        BufferedReader reader;
        Interpreter interpreter = Main.interpreter;
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
                    //System.out.println("Server Response: " +line);
                    Main.interpreter.inputInterpreter(line);

                }
            } catch (ArrayIndexOutOfBoundsException  | IOException e) {
                e.printStackTrace();
            }
        }
        //initiating the buffered Reader
        void buffReader() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (ArrayIndexOutOfBoundsException| IOException e) {
                e.printStackTrace();
            }
        }

    }

}



