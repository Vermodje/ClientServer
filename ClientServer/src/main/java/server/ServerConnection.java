package server;

import java.io.*;
import java.net.Socket;

public class ServerConnection extends  Thread {
    private Socket socket;
    private Server server;
    private BufferedReader in;
    private BufferedWriter out;
    public ServerConnection(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;

    }
    public void sendMessageToClient(String message) throws IOException {
        out.write(message + "\n");
        out.flush();
    }
    /*public void sendMessageToAllClients(String message) throws IOException {
        for (int i = 0; i < server.getConnections().size(); i++){
            ServerConnection serverConnection = server.getConnections().get(i);
            serverConnection.sendMessageToClient(message);
        }
    }*/

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }

        while (!socket.isClosed()){
            try {
                String clientMessage = in.readLine();
                sendMessageToClient(clientMessage);
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
