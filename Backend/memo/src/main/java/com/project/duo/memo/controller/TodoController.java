package com.project.duo.memo.controller;

import com.project.duo.memo.domain.Todo;
import com.project.duo.memo.domain.User;
import com.project.duo.memo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getUserList(){
        return todoService.getAllTodos();
    }

    @GetMapping("/todos/{id}")
    public Todo getUser(@PathVariable Long id){
        return todoService.getTodoById(id);
    }




/*
    @PostMapping("/todos")
    public ResponseEntity<?> postTodo(@RequestBody TodoRequest todoRequest){

    }

    @PutMapping("/todos")
    public ResponseEntity<?> putTodo(@RequestBody TodoRequest todoRequest){

    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }
*/
}
