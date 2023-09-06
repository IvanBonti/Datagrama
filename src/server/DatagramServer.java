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

            // Fase 2: Confirmación de solicitud
            int confirmacionSolicitudPort = 9877;
            DatagramSocket confirmacionSolicitudSocket = new DatagramSocket();
            String confirmacionSolicitud = "Confirmación de solicitud recibida";
            byte[] confirmacionSolicitudData = confirmacionSolicitud.getBytes();
            DatagramPacket confirmacionSolicitudPacket = new DatagramPacket(confirmacionSolicitudData, confirmacionSolicitudData.length, clientAddress, confirmacionSolicitudPort);
            confirmacionSolicitudSocket.send(confirmacionSolicitudPacket);

            // Fase 3: Respuesta
            int respuestaPort = 9878;
            DatagramSocket respuestaSocket = new DatagramSocket(respuestaPort);
            byte[] respuestaData = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(respuestaData, respuestaData.length);
            respuestaSocket.receive(respuestaPacket);

            String respuesta = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());
            System.out.println("Respuesta enviada al cliente: " + respuesta);

            // Fase 4: Confirmación de respuesta
            int confirmacionRespuestaPort = 9879;
            DatagramSocket confirmacionRespuestaSocket = new DatagramSocket(confirmacionRespuestaPort);
            byte[] confirmacionRespuestaData = new byte[1024];
            DatagramPacket confirmacionRespuestaPacket = new DatagramPacket(confirmacionRespuestaData, confirmacionRespuestaData.length);
            confirmacionRespuestaSocket.receive(confirmacionRespuestaPacket);

            String confirmacionRespuestaCliente = new String(confirmacionRespuestaPacket.getData(), 0, confirmacionRespuestaPacket.getLength());
            System.out.println("Confirmación de respuesta del cliente: " + confirmacionRespuestaCliente);

            // Cierre de sockets
            solicitudSocket.close();
            confirmacionSolicitudSocket.close();
            respuestaSocket.close();
            confirmacionRespuestaSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
