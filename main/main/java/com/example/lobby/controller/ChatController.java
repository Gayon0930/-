package com.example.lobby.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public String sendMessage(String message) {
        return message; // 메시지를 모든 클라이언트에게 브로드캐스트
    }
}
