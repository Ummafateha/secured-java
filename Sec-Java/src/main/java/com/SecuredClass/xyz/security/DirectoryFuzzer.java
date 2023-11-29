package com.SecuredClass.xyz.security;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DirectoryFuzzer {
    public static void enumerateDirectories(String url) {
        try {

            String[] commonDirectories = {"admin", "test", "dev", "backup"};

            for (String directory : commonDirectories) {
                String testUrl = url + "/" + directory;
                HttpURLConnection connection = (HttpURLConnection) new URL(testUrl).openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("Directory found: " + testUrl);
                }

                connection.disconnect();
            }
        } catch (IOException e) {
            System.out.println("Error during directory enumeration: " + e.getMessage());
        }
    }
}
