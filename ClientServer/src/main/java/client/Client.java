package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in;
    private static BufferedWriter out;
    public static void main(String[] args) throws IOException, InterruptedException {

                clientSocket = new Socket("localhost", 4004);

                reader = new BufferedReader(new InputStreamReader(System.in));

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                while (!clientSocket.isClosed()){
                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");

                    String word = reader.readLine();
                    out.write(word + "\n");
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


    }
}
