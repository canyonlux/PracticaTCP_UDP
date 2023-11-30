package aplicacion2;

import java.io.*;
import java.net.*;
import java.util.Base64;

public class UDPServer {
    public static void main(String args[]) throws IOException {
        int port = 6789; // Puerto en el que escucha el servidor
        DatagramSocket serverSocket = new DatagramSocket(port);

        byte[] receiveData = new byte[1024];
        System.out.println("Servidor UDP iniciado en el puerto " + port);

        while (true) {
            // Recibir paquete UDP
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            // Procesar datos recibidos
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            message = message.substring(1, message.length() - 1); // Eliminar delimitadores
            String decodedMessage = new String(Base64.getDecoder().decode(message)); // Decodificar mensaje

            // Escribir mensaje decodificado en un archivo
            try (FileWriter fw = new FileWriter("output.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(decodedMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Mensaje recibido y guardado: " + decodedMessage);
        }
    }
}
