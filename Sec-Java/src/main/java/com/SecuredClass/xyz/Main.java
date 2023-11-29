package com.SecuredClass.xyz;

import com.SecuredClass.xyz.security.*;
import com.SecuredClass.xyz.security.PortScanner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static String targetUrl;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter your email address: ");
        String userEmail = scanner.nextLine();

        do {
            System.out.println("\nOptions:");
            System.out.println("1. Update Target Domain Address");
            System.out.println("2. Check SSL Configuration");
            System.out.println("3. Check Security Headers");
            System.out.println("4. Enumerate Directories");
            System.out.println("5. Check Links");
            System.out.println("6. Scan Ports");
            System.out.println("7. Perform Whois Lookup");
            System.out.println("8. Exit");

            System.out.print("Enter your choice (1-8): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updateTargetDomain(scanner);
                    break;
                case 2:
                    logActivity(userName, targetUrl, "Check SSL Configuration");
                    SSLSecurityChecker.checkSSLConfiguration(targetUrl);
                    break;
                case 3:
                    logActivity(userName, targetUrl, "Check Security Headers");
                    HeaderSecurityChecker.checkSecurityHeaders(targetUrl);
                    break;
                case 4:
                    logActivity(userName, targetUrl, "Enumerate Directories");
                    DirectoryFuzzer.enumerateDirectories(targetUrl);
                    break;
                case 5:
                    logActivity(userName, targetUrl, "Check Links");
                    LinkChecker.checkLinks(targetUrl);
                    break;
                case 6:
                    logActivity(userName, targetUrl, "Scan Ports");
                    PortScanner.scanPorts(targetUrl);
                    break;
                case 7:
                    logActivity(userName, targetUrl, "Perform Whois Lookup");
                    WhoisLookup.performWhoisLookup(targetUrl);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }

        } while (true);
    }

    private static void updateTargetDomain(Scanner scanner) {
        System.out.print("Enter the new target domain address: ");
        String newUrl = scanner.nextLine();


        try {
            new URL(newUrl);
            targetUrl = newUrl;
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL format: " + e.getMessage());
            targetUrl = null;
        }
    }




    private static void logActivity(String userName, String targetUrl, String activity) {
        System.out.println("User: " + userName + ", Domain: " + targetUrl + ", Activity: " + activity);
    }
}
