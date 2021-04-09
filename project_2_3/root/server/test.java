package root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    static Socket socket;
    static PrintWriter writer;

    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 7789);
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.execute(new ReadLines());
            executorService.execute(new Conversation());
            executorService.shutdown();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        }

    public static class ReadLines implements Runnable {
        BufferedReader reader;

        @Override
        public void run() {
            buffReader();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    parseOutput(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //initiating the buffered Reader
        void buffReader(){
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //parse output
        void parseOutput(String line){
            if(line.contains("ERR")){
                System.out.println("error: "+line);
            } else if(line.contains("SVR")){
                System.out.println("server: "+line);
            } else if(line.contains("OK")){
                System.out.println("Command accepted");
            } else {
                System.out.println(line);
            }
        }
    }


    private static class Conversation implements Runnable {
        @Override
        public void run() {
            //scanner for command
            Scanner scanner = new Scanner(System.in);
            String line = "";
            while (/*line!=null*/!line.equals("logout")) {
                line = this.commandInput(scanner);
                this.sendInput(line, writer);
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //send output stream
        void sendInput(String command, PrintWriter writer) {
            System.out.println("sending command: " + command);
            writer.println(command);
        }

        //scan for command input
        String commandInput(Scanner scanner) {
            return scanner.nextLine();
        }

    }
}