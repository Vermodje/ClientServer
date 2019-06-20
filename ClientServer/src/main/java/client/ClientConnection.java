package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class ClientConnection {
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private Scanner console;

    ClientConnection(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            console = new Scanner(System.in);
            startConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startConnection() {
        String message;
        while (!socket.isClosed()) {
            try {
                message = console.nextLine();
                out.write(message + "\n");
                out.flush();
                if(message.equalsIgnoreCase("Bue")){
                    closeConnection();
                    break;
                }
                else {
                    System.out.println(in.readLine());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeConnection();
    }

    private void closeConnection() {
        console.close();
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
