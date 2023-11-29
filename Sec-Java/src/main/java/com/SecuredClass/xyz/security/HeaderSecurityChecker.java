package com.SecuredClass.xyz.security;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HeaderSecurityChecker {
    public static void checkSecurityHeaders(String url) {
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            Map<String, java.util.List<String>> headers = connection.getHeaderFields();


            if (headers.containsKey("Strict-Transport-Security")) {
                System.out.println("Strict-Transport-Security header is present.");
            } else {
                System.out.println("Strict-Transport-Security header is missing.");
            }

            if (headers.containsKey("Content-Security-Policy")) {
                System.out.println("Content-Security-Policy header is present.");
            } else {
                System.out.println("Content-Security-Policy header is missing.");
            }




            connection.disconnect();
        } catch (IOException e) {
            System.out.println("Error checking security headers: " + e.getMessage());
        }
    }
}
