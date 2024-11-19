package me.docherri.springbootdeveloper.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TodoListDto {
    private List<String> tasks;
}
