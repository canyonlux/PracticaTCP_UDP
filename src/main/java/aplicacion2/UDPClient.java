package aplicacion2;

import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Scanner;

public class UDPClient {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int port = 6789; // Puerto del servidor
        DatagramSocket clientSocket = new DatagramSocket();

        while (true) {
            System.out.print("Ingrese un mensaje: ");
            String message = scanner.nextLine();
            String encodedMessage = "#" + Base64.getEncoder().encodeToString(message.getBytes()) + "#";

            byte[] sendData = encodedMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket); // Enviar mensaje

            System.out.println("Mensaje enviado al servidor.");
        }
    }
}
