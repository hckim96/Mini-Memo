package com.project.duo.memo.controller;

import com.project.duo.memo.domain.User;
import com.project.duo.memo.domain.UserRequest;
import com.project.duo.memo.service.MemoService;
import com.project.duo.memo.service.TodoService;
import com.project.duo.memo.service.UserService;
import com.project.duo.memo.util.JwtAuthenticationTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;
    private final JwtAuthenticationTokenProvider tokenProvider;


    @PostConstruct
    public void testInit(){

    }


    @PostMapping("/users/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequest userRequest){
        Map<String, Object> map = new LinkedHashMap<>();
        boolean result;
        logger.info(userRequest.getUsername());
        logger.info(userRequest.getPassword());

        User user = new User(userRequest.getUsername(), userRequest.getPassword());
        result = userService.signInUser(user);
        map.put("result", result);

        if(result) {
            map.put("token", tokenProvider.issue(user.getId()).getToken());
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/users/validateUsername")
    public ResponseEntity<?> validateUsername(@RequestBody UserRequest userRequest){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", userService.checkUserNameDuplicated(userRequest.getUsername()));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/users/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest){
        Map<String, Object> map = new LinkedHashMap<>();
        User user = new User(userRequest.getUsername(), userRequest.getPassword());

        map.put("result", userService.signUpUser(user));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

/*


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
 */
}
