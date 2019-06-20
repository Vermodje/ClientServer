package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    private static ServerSocket server;
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    public static void main(String[] args) {

        //int port = 5050;
        try {
            logger.info("Server started");
            server = new ServerSocket(5050);
            server.setReuseAddress(true);
            while (true) {
                Socket socket = server.accept();
                System.out.println("New client connected " + socket.getInetAddress().getHostAddress());
                ClientHandler clientSocket = new ClientHandler(socket);
                new Thread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
