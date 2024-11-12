package com.example.lobby.repository;

import com.example.lobby.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // 특정 이름을 가진 모든 방 반환
    List<Room> findAllByName(String name);

    // 특정 이름을 가진 방이 존재하는지 확인 (단일 결과)
    boolean existsByName(String name);

    Optional<Object> findByName(String name);
}
