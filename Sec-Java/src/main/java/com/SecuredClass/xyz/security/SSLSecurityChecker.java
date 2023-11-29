package com.SecuredClass.xyz.security;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class SSLSecurityChecker {
    public static void checkSSLConfiguration(String url) {
        try {
            URI targetUri = new URI(url);
            URL targetUrl = targetUri.toURL();

            HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
            connection.connect();

            if (connection instanceof javax.net.ssl.HttpsURLConnection) {
                javax.net.ssl.HttpsURLConnection httpsConnection = (javax.net.ssl.HttpsURLConnection) connection;
                System.out.println("SSL/TLS is configured correctly.");
                System.out.println("Cipher Suite: " + httpsConnection.getCipherSuite());
                System.out.println("Protocol: " + httpsConnection.getURL().getProtocol());
            } else {
                System.out.println("Not an HTTPS connection. Cannot perform SSL/TLS checks.");
            }

            connection.disconnect();
        } catch (IOException | java.net.URISyntaxException e) {
            System.out.println("SSL/TLS misconfiguration detected. Error: " + e.getMessage());
        }
    }
}
