package me.docherri.springbootdeveloper.repository;


import me.docherri.springbootdeveloper.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByName(String name);
    boolean existsByName(String name);
    Optional<Object> findByName(String name);
}