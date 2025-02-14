package com.chatbot.finance.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatbotController {

    private final String CHATBOT_API_URL = "http://127.0.0.1:5000/chatbot";

    @PostMapping("/chatbot")
    public ResponseEntity<String> getChatbotResponse(@RequestBody Map<String, String> request) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.exchange(CHATBOT_API_URL, HttpMethod.POST, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}

