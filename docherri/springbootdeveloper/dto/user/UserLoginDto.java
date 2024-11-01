package me.docherri.springbootdeveloper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserLoginDto {
    private String loginId;
    private String password;
}
