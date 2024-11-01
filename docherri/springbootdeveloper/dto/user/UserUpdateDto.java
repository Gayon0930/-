package me.docherri.springbootdeveloper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserUpdateDto { //업데이트 가능 정보들만
    private String password;
    private String nickname;
    private String profileMessage;
    private String profilePic;
}
