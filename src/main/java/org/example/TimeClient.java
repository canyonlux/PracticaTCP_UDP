package org.example;

import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost"; // Dirección del servidor
        int port = 6789; // Puerto del servidor

        // Crear un socket para conectar con el servidor
        Socket socket = new Socket(hostName, port);
        System.out.println("Conectado al servidor");

        // Crear streams para leer y escribir datos
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Enviar petición al servidor
        out.println("time");

        // Leer y mostrar la respuesta del servidor
        String serverResponse = in.readLine();
        System.out.println("Hora actual del servidor: " + serverResponse);

        // Cerrar conexión
        socket.close();
    }
}
