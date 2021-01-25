package com.project.duo.memo.service;

import com.project.duo.memo.domain.Todo;
import com.project.duo.memo.domain.User;
import com.project.duo.memo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo save(Todo todo1) {
        return todoRepository.save(todo1);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id);
    }
}
