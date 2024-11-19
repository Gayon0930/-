package me.docherri.springbootdeveloper.controller.rest;

import lombok.RequiredArgsConstructor;
import me.docherri.springbootdeveloper.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{loginId}/todolist")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;

    //할 일 추가
    @PostMapping("/add")
    public ResponseEntity<Void> addTask(@PathVariable String loginId, @RequestParam String task) {
        todoListService.addTask(loginId, task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //할 일 삭제
    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<Void> deleteTask(@PathVariable String loginId, @PathVariable int idx) {
        todoListService.deleteTask(loginId, idx);
        return ResponseEntity.ok().build();
    }

    //할 일 수정
    @PutMapping("/update/{idx}")
    public ResponseEntity<Void> updateTask(@PathVariable String loginId, @PathVariable int idx, @RequestParam String updateTask) {
        todoListService.updateTask(loginId, idx, updateTask);
        return ResponseEntity.ok().build();
    }

    //목록 조회
    @GetMapping
    public ResponseEntity<List<String>> getTodoList(@PathVariable String loginId) {
        List<String> tasks = todoListService.getTodoList(loginId);
        return ResponseEntity.ok(tasks);
    }
}