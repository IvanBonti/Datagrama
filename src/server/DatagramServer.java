package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramServer {
    public static void main(String[] args) {
        try {
            // Fase 1: Solicitud
            int solicitudPort = 9876;
            DatagramSocket solicitudSocket = new DatagramSocket(solicitudPort);
            byte[] solicitudData = new byte[1024];
            DatagramPacket solicitudPacket = new DatagramPacket(solicitudData, solicitudData.length);
            solicitudSocket.receive(solicitudPacket);

            String solicitud = new String(solicitudPacket.getData(), 0, solicitudPacket.getLength());
            System.out.println("Solicitud recibida: " + solicitud);

            InetAddress clientAddress = solicitudPacket.getAddress();
            int clientPort = solicitudPacket.getPort();

            // Fase 2: Confirmación
            int confirmacionPort = 9877;
            DatagramSocket confirmacionSocket = new DatagramSocket();
            String confirmacion = "Confirmación de solicitud recibida";
            byte[] confirmacionData = confirmacion.getBytes();
            DatagramPacket confirmacionPacket = new DatagramPacket(confirmacionData, confirmacionData.length, clientAddress, confirmacionPort);
            confirmacionSocket.send(confirmacionPacket);

            // Fase 3: Respuesta
            int respuestaPort = 9878;
            DatagramSocket respuestaSocket = new DatagramSocket();
            String respuesta = "Respuesta a la solicitud";
            byte[] respuestaData = respuesta.getBytes();
            DatagramPacket respuestaPacket = new DatagramPacket(respuestaData, respuestaData.length, clientAddress, respuestaPort);
            respuestaSocket.send(respuestaPacket);

            // Cierre de sockets
            solicitudSocket.close();
            confirmacionSocket.close();
            respuestaSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
