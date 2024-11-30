package me.docherri.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.docherri.springbootdeveloper.domain.User;
import me.docherri.springbootdeveloper.dto.user.UserCreateDto;
import me.docherri.springbootdeveloper.dto.user.UserProfileDto;
import me.docherri.springbootdeveloper.dto.user.UserUpdateDto;
import me.docherri.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    //유저 생성
    public void createUser(UserCreateDto userCreateDto) {
        if (userRepository.findByNickname(userCreateDto.getUsername()).isPresent()) {
            throw new RuntimeException("Nickname already exists");
        }
        User user = new User();
        user.setNickname(userCreateDto.getUsername());
       // user.setGender(userCreateDto.getGender());
        userRepository.save(user);
    }

    //유저 조회
    public Optional<UserProfileDto> getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .map(user -> new UserProfileDto(
                        user.getNickname(),
                        user.getGender(),
                        user.getProfileMessage()
                ));
    }
    //유저 업데이트
    public void userUpdate(String nickname, UserUpdateDto update) throws IOException {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("User not found : " + nickname));

        if(update.getProfileMessage() != null)
            user.setProfileMessage(update.getProfileMessage());

        userRepository.save(user);
    }

}
