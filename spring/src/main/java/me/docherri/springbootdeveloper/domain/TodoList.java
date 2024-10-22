package me.docherri.springbootdeveloper.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TodoList { //최대 5개
    private List<String> tasks = new ArrayList<>();
    public void addTasks(String task){
        if(tasks.size()<5) {
            tasks.add(task);
        } else{
        //클라이언트에 메시지 반환(추가불가) -> 컨트롤러
        }
    }
    public void deleteTasks(int idx){
        if(idx >= 0 && idx < tasks.size()){
            tasks.remove(idx);
        } else{
            //클라이언트에 메시지 반환(인덱스 오류)
        }
    }

}
