package com.SecuredClass.xyz.security;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WhoisLookup {

    private static final String API_KEY = "at_a95AHmn88QXY1zTQY6r1Bh2QHxQWz";
    private static final String WHOIS_API_URL = "https://www.whoisxmlapi.com/whoisserver/WhoisService";

    public static void performWhoisLookup(String domain) {
        try {

            URI uri = new URI(domain);
            String domainName = uri.getHost();

            if (domainName == null) {
                System.out.println("Error parsing the domain name.");
                return;
            }


            String apiUrl = WHOIS_API_URL + "?apiKey=" + API_KEY + "&domainName=" + domainName + "&outputFormat=json";


            HttpClient client = HttpClient.newHttpClient();


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("Error performing Whois lookup: " + e.getMessage());
        }
    }
}
