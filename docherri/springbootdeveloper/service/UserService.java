package me.docherri.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.docherri.springbootdeveloper.domain.TodoList;
import me.docherri.springbootdeveloper.domain.User;
import me.docherri.springbootdeveloper.dto.user.UserCreateDto;
import me.docherri.springbootdeveloper.dto.user.UserLoginDto;
import me.docherri.springbootdeveloper.dto.user.UserProfileDto;
import me.docherri.springbootdeveloper.dto.user.UserUpdateDto;
import me.docherri.springbootdeveloper.repository.TodoListRepository;
import me.docherri.springbootdeveloper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TodoListRepository todoListRepository;
    private final PasswordEncoder passwordEncoder;

    //유저 생성
    public void createUser(UserCreateDto userCreateDto) {
        if (userRepository.findByLoginId(userCreateDto.getLoginId()).isPresent()) {
            throw new RuntimeException("Login ID already exists");
        }
        User user = new User();

        user.setLoginId(userCreateDto.getLoginId());
        user.setNickname(userCreateDto.getNickname());
        user.setGender(userCreateDto.getGender());
        user.setPassword(userCreateDto.getPassword());
        user.setProfilePic(userCreateDto.getProfilePic());

        userRepository.save(user);

        TodoList todoList = new TodoList();
        todoList.setUser(user);
        user.setTodoList(todoList);

        todoListRepository.save(todoList);
        userRepository.save(user);
    }

    //유저 삭제
    public void deleteUser(String loginId){
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found : " + loginId));
        userRepository.deleteById(user.getUid());
    }

    //유저 조회
    public Optional<UserProfileDto> getUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(user -> new UserProfileDto(
                        user.getNickname(),
                        user.getGender(),
                        user.getProfileMessage(),
                        user.getProfilePic() != null ? user.getProfilePic() : null
                ));
    }

    //유저 업데이트
    public void userUpdate(String loginId, UserUpdateDto update){
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found : " + loginId));

        if(update.getNickname() != null)
            user.setNickname(update.getNickname());
        if(update.getPassword() != null)
            user.setPassword(update.getPassword());
        if(update.getProfileMessage() != null)
            user.setProfileMessage(update.getProfileMessage());
        if(update.getProfilePic() != null)
            user.setProfilePic(update.getProfilePic());

        userRepository.save(user);
    }

    //로그인
    public User userLogin(UserLoginDto login){
    //1.아이디 조회
        User user = userRepository.findByLoginId(login.getLoginId())
                .orElseThrow(() -> new RuntimeException("User not Found"));

        //비밀번호 검증
        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong Password");
        }
        //성공시 유저반환
        return user;
    }

}
