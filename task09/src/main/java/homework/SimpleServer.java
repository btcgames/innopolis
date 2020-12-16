package homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

public class SimpleServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            System.out.println("Server started");
            Socket client = ss.accept();
            System.err.println("Client accepted");
            readInputHeaders(client);
            Thread.sleep(100);
            client.close();
        }
    }


    private static void writeResponse(Socket client) throws IOException {

        String path = new File(".").getAbsolutePath();
        String[] list = new File(path).list();
        assert list != null;
        String response = Arrays.asList(list).toString();

        OutputStream os = client.getOutputStream();
        os.write(response(response).getBytes());
        os.flush();
    }

    private static String response(String resp) {
        Date date = new Date();
        String start = "HTTP/1.1 200 OK\r\n";
        String header = "Date: " + date.toString() + "\r\n";
        header += "Content-Type: text/html\r\n";
        header += "Content-length: " + resp.length() + "\r\n";
        header += "\r\n";
        return start + header + resp;
    }

    private static void readInputHeaders(Socket client) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String str = br.readLine();
                    if (str != null && str.contains("GET")) {
                        writeResponse(client);
                    } else {
                        client.getOutputStream().write(response("Error Code: 404").getBytes());
                        client.getOutputStream().flush();
                    }
                } catch (IOException e) {
                    System.out.println("Socket is closed");
                }
            }
        }).start();
    }
}
