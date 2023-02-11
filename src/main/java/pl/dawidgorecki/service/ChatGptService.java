package pl.dawidgorecki.service;

import com.google.gson.Gson;
import pl.dawidgorecki.model.ChatGptRequest;
import pl.dawidgorecki.model.ChatGptResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatGptService {
    public static final URI API_URI = URI.create("https://api.openai.com/v1/completions");
    private final Gson gson = new Gson();
    private final String apiKey;

    public ChatGptService(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String search(String searchString) throws IOException, InterruptedException {
        ChatGptRequest chatGptRequest = new ChatGptRequest(
                "text-davinci-003",
                searchString,
                1,
                100
        );

        String body = gson.toJson(chatGptRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(API_URI)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ChatGptResponse chatGptResponse = gson.fromJson(response.body(), ChatGptResponse.class);
            String answer = chatGptResponse.getChoices()[0].getText();

            if (!answer.isEmpty()) {
                return answer.replace("\n", "").trim();
            } else {
                return "no answers found";
            }
        } else {
            return String.valueOf(response.statusCode());
        }
    }
}
