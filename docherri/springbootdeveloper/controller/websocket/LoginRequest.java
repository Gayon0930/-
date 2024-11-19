package me.docherri.springbootdeveloper.controller.websocket;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data // Lombok 어노테이션으로 getter, setter, toString, equals 등을 자동 생성
public class LoginRequest {
    private String username;
}