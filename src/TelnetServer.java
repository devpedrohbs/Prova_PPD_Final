package src;
import java.io.*;
import java.net.*;
import java.util.*;

public class TelnetServer {
    public static final int NUMBER_OF_PHILOSOPHERS = 5; 
    public static Fork[] forks = new Fork[NUMBER_OF_PHILOSOPHERS]; 
    public static List<Philosopher> philosophers = new ArrayList<>(); 

    public static void main(String[] args) {
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            forks[i] = new Fork();
        }

 
        try (ServerSocket serverSocket = new ServerSocket(23)) {
            System.out.println("Servidor Telnet iniciado na porta 23...");
            while (true) {
                new PhilosopherManager(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Philosopher> getPhilosophers() {
        return philosophers;
    }
}
