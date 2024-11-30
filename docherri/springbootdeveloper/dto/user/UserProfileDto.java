package me.docherri.springbootdeveloper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.docherri.springbootdeveloper.domain.User;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserProfileDto {
    private String username;
    private User.Gender gender;
    private String profileMessage;
}
