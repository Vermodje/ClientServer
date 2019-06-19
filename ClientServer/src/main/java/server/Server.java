package server;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private  Socket clientSocket;
    private  ServerSocket server;
    private List<ServerConnection> connections = new ArrayList<ServerConnection>();

    public Server() throws IOException {
        server = new ServerSocket(4004);
        clientSocket = server.accept();
        while (!clientSocket.isClosed()){
            ServerConnection serverConnection = new ServerConnection(clientSocket, this);
            serverConnection.start();
            connections.add(serverConnection);
        }

    }

    public List<ServerConnection> getConnections() {
        return connections;
    }
    /*public static void main(String[] args) throws IOException, InterruptedException {
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

    }*/
}
