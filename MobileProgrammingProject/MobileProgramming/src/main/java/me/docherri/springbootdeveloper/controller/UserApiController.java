package me.docherri.springbootdeveloper.controller;


import lombok.RequiredArgsConstructor;
import me.docherri.springbootdeveloper.domain.User;
import me.docherri.springbootdeveloper.dto.user.UserCreateDto;
import me.docherri.springbootdeveloper.dto.user.UserLoginDto;
import me.docherri.springbootdeveloper.dto.user.UserProfileDto;
import me.docherri.springbootdeveloper.dto.user.UserUpdateDto;
import me.docherri.springbootdeveloper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    //생성
    @PostMapping("/api/users")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateDto user){
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //조회
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserProfileDto> getUser(@PathVariable("id") String id){
        UserProfileDto getUser = userService.getUserByLoginId(id);

        return ResponseEntity.ok()
                .body(getUser);
    }


    //삭제
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable("id") long id){
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }


    //업데이트
    @PutMapping("/api/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id, @RequestBody UserUpdateDto user){
        userService.userUpdate(id, user);

        return ResponseEntity.ok().build();
    }



    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserProfileDto> login(@RequestBody UserLoginDto loginRequest) {
        // 로그인 서비스 호출
        User user = userService.userLogin(loginRequest);

        // 유저 정보를 UserProfileDto로 변환하여 반환
        UserProfileDto response = new UserProfileDto(
                user.getNickname(),
                user.getGender(),
                user.getProfileMessage(),
                user.getProfilePic()  // 이미지 경로를 가져옴
        );

        return ResponseEntity.ok(response);  // HTTP 200과 함께 유저 정보 반환
    }

}
