package homework.server;

import homework.network.TCPConnection;
import homework.network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements TCPConnectionListener {
    public static void main(String[] args) {
        new Server();
    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    private Server() {
        System.out.println("Server running...");
        try (ServerSocket serverSocket = new ServerSocket(8080)) { // слушает порт и приним вх соед-я
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept()); // accept ждет новое соед-е
                } catch (IOException e) {
                    System.out.println("TCPException exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception exception) {
        System.out.println("TCPConnection exception: " + exception);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        for (int i = 0; i < connections.size(); i++) {
            connections.get(i).sendString(value);
        }
    }
}
