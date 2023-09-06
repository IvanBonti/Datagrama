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

            // Fase 2: Confirmación de solicitud
            int confirmacionSolicitudPort = 9877;
            DatagramSocket confirmacionSolicitudSocket = new DatagramSocket(confirmacionSolicitudPort);
            byte[] confirmacionSolicitudData = new byte[1024];
            DatagramPacket confirmacionSolicitudPacket = new DatagramPacket(confirmacionSolicitudData, confirmacionSolicitudData.length);
            confirmacionSolicitudSocket.receive(confirmacionSolicitudPacket);

            String confirmacionSolicitud = new String(confirmacionSolicitudPacket.getData(), 0, confirmacionSolicitudPacket.getLength());
            System.out.println("Confirmación de solicitud del servidor: " + confirmacionSolicitud);

            // Fase 3: Respuesta
            int respuestaPort = 9878;
            DatagramSocket respuestaSocket = new DatagramSocket(respuestaPort);
            byte[] respuestaData = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(respuestaData, respuestaData.length);
            respuestaSocket.receive(respuestaPacket);

            String respuesta = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());
            System.out.println("Respuesta final del servidor: " + respuesta);

            // Fase 4: Confirmación de respuesta
            int confirmacionRespuestaPort = 9879;
            DatagramSocket confirmacionRespuestaSocket = new DatagramSocket();
            String confirmacionRespuesta = "Confirmación de respuesta recibida";
            byte[] confirmacionRespuestaData = confirmacionRespuesta.getBytes();
            DatagramPacket confirmacionRespuestaPacket = new DatagramPacket(confirmacionRespuestaData, confirmacionRespuestaData.length, serverAddress, confirmacionRespuestaPort);
            confirmacionRespuestaSocket.send(confirmacionRespuestaPacket);

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
