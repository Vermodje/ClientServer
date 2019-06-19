package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket = new Socket("localhost", 4004);;
    private ClientConnection clientConnection;
    private BufferedReader console;

    public  Client() throws IOException {
        clientConnection = new ClientConnection(clientSocket, this);
        clientConnection.start();
        initialize();
    }
    public void initialize() throws IOException {
        console = new BufferedReader(new InputStreamReader(System.in));
        while (!clientSocket.isClosed()){
            String message = console.readLine();
            if(message.equalsIgnoreCase("quit")){
                break;
            }
            clientConnection.sendMessageToServer(message);
        }
        clientConnection.close();
    }

    /*public static void main(String[] args) throws IOException, InterruptedException {

                clientSocket = new Socket("localhost", 4004);

                reader = new BufferedReader(new InputStreamReader(System.in));

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                while (!clientSocket.isClosed()){
                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");

                    String word = reader.readLine();
                    out.write(word + "\n" );
                    if(word.equalsIgnoreCase("quit")){
                        out.flush();
                        System.out.println(in.readLine());
                        Thread.sleep(3000);
                        break;
                    }
                    out.flush();
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                }

                clientSocket.close();
                in.close();
                out.close();


    }*/
}
