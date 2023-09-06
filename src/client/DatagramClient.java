package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramClient {
    public static void main(String[] args) {
        try {
            // Fase 1: Solicitud
            int solicitudPort = 9876;
            DatagramSocket solicitudSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            String solicitud = "Solicitud de datos";
            byte[] solicitudData = solicitud.getBytes();

            DatagramPacket solicitudPacket = new DatagramPacket(solicitudData, solicitudData.length, serverAddress, solicitudPort);
            solicitudSocket.send(solicitudPacket);

            // Fase 2: Confirmación
            int confirmacionPort = 9877;
            DatagramSocket confirmacionSocket = new DatagramSocket(confirmacionPort);
            byte[] confirmacionData = new byte[1024];
            DatagramPacket confirmacionPacket = new DatagramPacket(confirmacionData, confirmacionData.length);
            confirmacionSocket.receive(confirmacionPacket);

            String confirmacion = new String(confirmacionPacket.getData(), 0, confirmacionPacket.getLength());
            System.out.println("Confirmación del servidor: " + confirmacion);

            // Fase 3: Respuesta
            int respuestaPort = 9878;
            DatagramSocket respuestaSocket = new DatagramSocket(respuestaPort);
            byte[] respuestaData = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(respuestaData, respuestaData.length);
            respuestaSocket.receive(respuestaPacket);

            String respuesta = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());
            System.out.println("Respuesta final del servidor: " + respuesta);

            // Cierre de sockets
            solicitudSocket.close();
            confirmacionSocket.close();
            respuestaSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
