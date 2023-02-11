package pl.dawidgorecki.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatGptRequest {
    private final String model;
    private final String prompt;
    private final int temperature;
    private final int max_tokens;
}
