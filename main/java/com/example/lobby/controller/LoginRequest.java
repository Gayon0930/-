package com.example.lobby.controller;

import lombok.Data;

@Data // Lombok 어노테이션으로 getter, setter, toString, equals 등을 자동 생성
public class LoginRequest {
    private String username;

    // Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
