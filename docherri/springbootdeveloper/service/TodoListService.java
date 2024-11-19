package me.docherri.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.docherri.springbootdeveloper.domain.TodoList;
import me.docherri.springbootdeveloper.domain.User;
import me.docherri.springbootdeveloper.repository.TodoListRepository;
import me.docherri.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    //투두리스트 생성
    @Transactional
    public void createTodoList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않음."));
        if (user.getTodoList() == null) {
            TodoList todoList = new TodoList();
            todoList.setUser(user);
            todoListRepository.save(todoList);
            user.setTodoList(todoList);
            userRepository.save(user);
        }
    }
    //항목 추가
    @Transactional
    public void addTask(String loginId, String task) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found : " + loginId));
        TodoList todoList = user.getTodoList();

        if (todoList.getTasks().size() < 5) {
            todoList.getTasks().add(task);
            todoListRepository.save(todoList);
        } else {
            throw new RuntimeException("최대 개수 초과!");
        }
    }

    //리스트 조회
    public List<String> getTodoList(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found : " + loginId));
        TodoList todoList = user.getTodoList();
        return todoList.getTasks();
    }

    //항목 수정
    @Transactional
    public void updateTask(String loginId, int index, String updateTask) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found : " + loginId));

        TodoList todoList = user.getTodoList();

        if (index >= 0 && index < todoList.getTasks().size()) {
            todoList.getTasks().set(index, updateTask);
            todoListRepository.save(todoList);
        } else {
            throw new RuntimeException("인덱스 오류 : 수정");
        }
    }

    //항목 삭제
    @Transactional
    public void deleteTask(String loginId, int index) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found : " + loginId));

        TodoList todoList = user.getTodoList();

        if (index >= 0 && index < todoList.getTasks().size()) {
            todoList.getTasks().remove(index);
            todoListRepository.save(todoList);
        } else {
            throw new RuntimeException("인덱스 오류 : 삭제");
        }
    }
}
