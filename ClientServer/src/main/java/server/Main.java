package server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Сервер запущен!");
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
