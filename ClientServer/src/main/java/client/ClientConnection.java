package client;

import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread {
    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;

    public ClientConnection(Socket socket, Client client) throws IOException {
        this.socket = socket;

    }
    public void sendMessageToServer(String message)  {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }

    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String reply = in.readLine();
            System.out.println(reply);
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }


        }
    }
    public void close(){
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
