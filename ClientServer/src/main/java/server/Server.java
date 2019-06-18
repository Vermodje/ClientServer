package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    public static void main(String[] args) throws IOException, InterruptedException {
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен!");

                clientSocket = server.accept();
        System.out.println("A new client is connected : " + clientSocket);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                while (!clientSocket.isClosed()) {
                    String word = in.readLine();
                    if(word.equalsIgnoreCase("quit")){
                        System.out.println("Client initialize connections suicide ...");
                        out.write("Вы покинули сервер");
                        out.flush();
                        Thread.sleep(3000);
                        break;
                    }
                    out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    out.flush();
                }

                    clientSocket.close();
                    in.close();
                    out.close();
                    System.out.println("Сервер закрыт!");
                    server.close();

    }
}
