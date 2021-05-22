package ru.tcreator;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

public class Client {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        final int port = 5002;
        try(Socket clientSocket = new Socket(host, port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in)))
        {
            System.out.println("Клиент подключился к серверу на порту: " + clientSocket.getPort());
            Optional<String> fromConsole = Optional.of(terminalReader.readLine());
            fromConsole.ifPresentOrElse(
                    element -> {
                        try {
                            out.write(element);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    },
                    () -> System.out.println("нет данных с консоли")

            );
//            Optional<String> fromServer = Optional.of(in.readLine());
//            fromServer.ifPresentOrElse(
//                    System.out::println,
//                    () -> System.out.println("Сервер не прислал данные")
//            );
//
//            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
