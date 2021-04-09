package root.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Connection {
    static Socket socket;
    static PrintWriter writer;
    private static LinkedBlockingQueue<String> commandQueue;
    private final String firstServerResponse = "Strategic Game Server Fixed [Version 1.1.0]\n" +
            "(C) Copyright 2015 Hanzehogeschool Groningen";
    //PrintWriter is voor output
    //BufferedReader is voor input

    public Connection() {
        commandQueue = new LinkedBlockingQueue<>();
    }

    public void connectToServer(String ip, int port) {
        System.out.println("ConnectToServer Werkt begin");
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

    public void printQueue() {
        System.out.println(commandQueue);
    }

    public void popQueue() {
        Object test = commandQueue.peek();
        commandQueue.remove(test);
    }

    public static String getFirstItemCommandQueue() {
        String returner = commandQueue.peek();
        System.out.println(returner);
        return returner;
    }


    static class Conversation implements Runnable {

        @Override
        public void run() {
            sendStringToServer(Connection.writer, Connection.getFirstItemCommandQueue());
        }

        public void sendStringToServer(PrintWriter writer, String command) {
            System.out.println("Sending command :" + command);
            System.out.println("writer " + writer);
            writer.println(command);
        }
    }

    static class ReadLines implements Runnable {
        BufferedReader reader;
        Interpreter interpreter = new Interpreter();

        @Override
        public void run() {
            buffReader();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
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



