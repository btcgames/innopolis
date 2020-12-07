package homework.client;

import homework.network.TCPConnection;
import homework.network.TCPConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {

    private static final String IP_ADDR = "localhost";
    private static final int PORT = 8080;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });
    }

    private final JTextArea log = new JTextArea();
    private final JTextField fieldNickName = new JTextField("Daniar");
    private final JTextField fieldInput = new JTextField();

    private TCPConnection connection;

    private ClientWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //окно посередине
        setAlwaysOnTop(true); //поверх др окон

        log.setEditable(false);
        log.setLineWrap(true); // авт перенос
        add(log, BorderLayout.CENTER);

        fieldInput.addActionListener(this);
        add(fieldInput, BorderLayout.SOUTH);
        add(fieldNickName, BorderLayout.NORTH);

        setVisible(true);
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            System.out.println("TCPConnection exception: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if("".equals(msg)) {
            return;
        }
        fieldInput.setText(null);
        connection.sendString(fieldNickName.getText() + ": " + msg);
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMassage("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMassage(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMassage("Connection close");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception exception) {
        System.out.println("TCPConnection exception: " + exception);
    }

    private synchronized void printMassage(final String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
