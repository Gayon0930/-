package me.docherri.springbootdeveloper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.docherri.springbootdeveloper.domain.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserProfileDto {
    private String nickname;
    private User.Gender gender;
    private String profileMessage;
    private String profilePicUrl;

}
