package com.project.duo.memo.service;

import com.project.duo.memo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TodoService {
    private TodoRepository todoRepository;

}
