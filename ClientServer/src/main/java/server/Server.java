package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            int count = 0;
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started");
            while (true){
                Socket clientSocket = serverSocket.accept();//Создаем канал для общения,
                // сервер ожидает подключение, когда кто-то перейдет по адресу http://127.0.0.1:8080
                BufferedReader buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //Создаем выходной буфер
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
                String req = buf.readLine();
                String resp  = "You asked me" + " " + req.length();

                //Записываем в буфер
                outputStreamWriter.write(resp);
                //Очищаем буфер
                outputStreamWriter.flush();
                outputStreamWriter.close();
                buf.close();
                //Закрываем буфер
                clientSocket.close();
            }

            //serverSocket.close();//После этого сервер будет закрыт
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
