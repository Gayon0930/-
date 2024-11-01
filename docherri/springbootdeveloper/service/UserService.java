package me.docherri.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.docherri.springbootdeveloper.domain.User;
import me.docherri.springbootdeveloper.dto.user.UserCreateDto;
import me.docherri.springbootdeveloper.dto.user.UserLoginDto;
import me.docherri.springbootdeveloper.dto.user.UserProfileDto;
import me.docherri.springbootdeveloper.dto.user.UserUpdateDto;
import me.docherri.springbootdeveloper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //유저 생성
    public void createUser(UserCreateDto userCreateDto) {
        User user = new User();

        user.setLoginId(userCreateDto.getLoginId());
        user.setNickname(userCreateDto.getNickname());
        user.setGender(userCreateDto.getGender());
        user.setPassword(userCreateDto.getPassword());
        user.setProfilePic(userCreateDto.getProfilePic());

        userRepository.save(user);
    }

    //유저 삭제
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //유저 조회 -> 이거 로그인아이디로 처리하게끔 수정
    public UserProfileDto getUserByLoginId(String loginid){
        User user = userRepository.findByLoginId(loginid)
                .orElseThrow(() -> new RuntimeException("User not Found"));

        return new UserProfileDto(user.getNickname(), user.getGender(),
                user.getProfileMessage(), user.getProfilePic());

    }

    //유저 업데이트, null값들 분기처리
    public void userUpdate(Long id, UserUpdateDto update){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not Found"));

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

        //2. 비밀번호 검증
        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong Password");
        }
        //3.성공시 유저반환 or 토큰
        return user;
    }

}
