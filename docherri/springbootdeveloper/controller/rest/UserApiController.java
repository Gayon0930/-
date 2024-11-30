package me.docherri.springbootdeveloper.controller.rest;


import lombok.RequiredArgsConstructor;
import me.docherri.springbootdeveloper.dto.user.UserCreateDto;
import me.docherri.springbootdeveloper.dto.user.UserProfileDto;
import me.docherri.springbootdeveloper.dto.user.UserUpdateDto;
import me.docherri.springbootdeveloper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;


    //조회
    @GetMapping("/api/users/{nickname}")
    public ResponseEntity<UserProfileDto> getUser(@PathVariable("nickname") String nickname) {
        return userService.getUserByNickname(nickname)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    //접속
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody UserCreateDto user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("Username cannot be empty");
        }
        else{ //닉네임 기준으로 유저 생성
            userService.createUser(user);
            return ResponseEntity.ok("Login successful");

        }
    }

    @PutMapping("/api/users/{nickname}")
    public ResponseEntity<Void> updateUser(@PathVariable("nickname") String nickname, @RequestBody UserUpdateDto user) throws IOException {
        userService.userUpdate(nickname, user);

        return ResponseEntity.ok().build();
    }



}
