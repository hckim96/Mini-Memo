package com.project.duo.memo.controller;

import com.project.duo.memo.domain.User;
import com.project.duo.memo.service.MemoService;
import com.project.duo.memo.service.TodoService;
import com.project.duo.memo.service.UserService;
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
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @Autowired
    public UserController(MemoService memoService, UserService userService, TodoService todoService) {
        this.userService = userService;
    }

    @PostConstruct
    public void testInit(){

    }

    @GetMapping("/users")
    public List<User> getUserList(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> postUser(@RequestBody UserRequest userRequest){

    }

    @PutMapping("/users")
    public ResponseEntity<?> putUser(@RequestBody UserRequest userRequest){

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
