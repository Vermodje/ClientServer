package client;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5050;
        ClientConnection connection = new ClientConnection(host, port);
    }
}

