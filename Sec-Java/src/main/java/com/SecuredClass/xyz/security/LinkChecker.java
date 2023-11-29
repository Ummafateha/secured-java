package com.SecuredClass.xyz.security;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LinkChecker {
    public static void checkLinks(String url) {
        try {

            Document document = Jsoup.connect(url).get();


            Elements links = document.select("a[href]");


            links.forEach(link -> {
                String href = link.attr("abs:href");


                if (isValidHttpUrl(href)) {
                    try {
                        HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
                        connection.setRequestMethod("GET");

                        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            System.out.println("Link is valid: " + href);
                        } else {
                            System.out.println("Broken link found: " + href);
                        }

                        connection.disconnect();
                    } catch (IOException e) {
                        System.out.println("Error checking link: " + e.getMessage());
                    }
                } else {

                    System.out.println("Ignoring non-HTTP/HTTPS link: " + href);
                }
            });
        } catch (IOException e) {
            System.out.println("Error fetching HTML content: " + e.getMessage());
        }
    }


    private static boolean isValidHttpUrl(String url) {
        try {
            URL u = new URL(url);
            return "http".equals(u.getProtocol()) || "https".equals(u.getProtocol());
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
