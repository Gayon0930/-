package com.example.lobby.controller;

import lombok.Data;

@Data // Lombok 어노테이션으로 getter, setter, toString, equals 등을 자동 생성
public class RoomRequest {
    private String name;
}
