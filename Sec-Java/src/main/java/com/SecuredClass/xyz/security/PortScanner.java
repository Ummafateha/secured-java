package com.SecuredClass.xyz.security;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

public class PortScanner {
    private static final int TIMEOUT_MILLISECONDS = 1000;

    public static void scanPorts(String url) {
        scanPorts(url, TIMEOUT_MILLISECONDS);
    }

    public static void scanPorts(String url, int timeout) {
        try {

            URL parsedUrl = new URL(url);
            String host = parsedUrl.getHost();


            int[] popularPorts = {21, 22, 25, 80, 443, 3443, 5403, 8080, 8888};

            for (int port : popularPorts) {
                try {
                    InetAddress inetAddress = InetAddress.getByName(host);
                    InetSocketAddress address = new InetSocketAddress(inetAddress, port);
                    Socket socket = new Socket();


                    socket.connect(address, timeout);


                    if (socket.isConnected()) {
                        System.out.println("Port " + port + " is open.");
                    }

                    socket.close();
                } catch (Exception e) {

                    System.out.println("Port " + port + " is closed or an error occurred: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing the URL: " + e.getMessage());
        }
    }
}
