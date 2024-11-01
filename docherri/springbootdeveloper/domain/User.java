package me.docherri.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    public enum Gender {
        MALE, FEMALE
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", updatable = false, unique = true)
    private long uid;

    @Column(name = "loginId", nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "profileMessage", nullable = true)
    private String profileMessage;

    @Column(name = "profilePic", nullable = true)
    private String profilePic;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "todolist_id")
    private TodoList todoList;
}
