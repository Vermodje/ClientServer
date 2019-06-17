package client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 8080);
            BufferedReader buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            outputStreamWriter.write("Hello World\n");
            outputStreamWriter.flush();
            System.out.println(buf.readLine());
            outputStreamWriter.close();
            buf.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
