package org.example;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 6789; // Puerto en el que escucha el servidor
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Servidor iniciado, esperando conexiones...");

        while (true) {
            // Aceptar una conexión del cliente
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

            // Crear streams para leer y escribir datos
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Leer mensaje del cliente
            String clientMessage = in.readLine();
            if ("time".equals(clientMessage)) {
                // Obtener la hora actual y enviarla al cliente
                String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                out.println(currentTime);
            }

            // Cerrar conexión
            clientSocket.close();
        }
    }
}
