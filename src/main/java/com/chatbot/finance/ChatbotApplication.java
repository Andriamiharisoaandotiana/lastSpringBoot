package com.chatbot.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatbotApplication.class, args);
        System.out.println("🚀 Chatbot Finance API démarrée sur http://localhost:8080 !");
    }
}
