package pl.dawidgorecki;

import pl.dawidgorecki.service.ChatGptService;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private static final String API_KEY = "your-key-here";

    public static void main(String[] args) throws IOException, InterruptedException {
        ChatGptService service = new ChatGptService(API_KEY);

        System.out.println("Java CLI application that use Chat GPT API");
        System.out.println("---");

        String queryString = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Enter a string to search for (or /q to quit): ");
            queryString = scanner.nextLine();

            if (queryString.equals("/q")) {
                break;
            }

            System.out.println("Searching...");
            String answer = service.search(queryString);
            System.out.println(answer);
        }
    }
}
