package me.docherri.springbootdeveloper.repository;

import me.docherri.springbootdeveloper.domain.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoListRepository extends JpaRepository <TodoList,Long>{
    //Optional<TodoList> findByUserId(Long userId);

}
