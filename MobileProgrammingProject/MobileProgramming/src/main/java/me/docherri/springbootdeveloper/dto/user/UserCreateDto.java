package me.docherri.springbootdeveloper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.docherri.springbootdeveloper.domain.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreateDto {
    private String loginId;
    private String password;
    private String nickname;
    private User.Gender gender;
    private String profilePic; //사진위치(nullable)

}
