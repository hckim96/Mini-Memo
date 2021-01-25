package com.project.duo.memo.controller;

import com.project.duo.memo.domain.ErrorResponse;
import com.project.duo.memo.domain.Memo;
import com.project.duo.memo.domain.MemoRequest;
import com.project.duo.memo.domain.Todo;
import com.project.duo.memo.domain.User;
import com.project.duo.memo.service.MemoService;
import com.project.duo.memo.service.TodoService;
import com.project.duo.memo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;
    private final MemoService memoService;
    private final TodoService todoService;

    @PostConstruct
    public void testInit(){

        User user1 = new User("kim", "password kim");
        User user2 = new User("lee", "password lee");


        Memo memo1 = new Memo("메모 1", "3");
        memo1.setUser(user1);
        Memo memo2 = new Memo("메모 2", "5");
        memo2.setUser(user1);
        Memo memo3 = new Memo("메모 3", "1");
        memo3.setUser(user2);

        Todo todo1 = new Todo("밥먹기", "34");
        Todo todo2 = new Todo("청소", "32");


        userService.save(user1);
        userService.save(user2);

        memoService.save(memo1);
        memoService.save(memo2);
        memoService.save(memo3);

        todoService.save(todo1);
        todoService.save(todo2);

    }

}
