package com.project.duo.memo.controller;

import com.project.duo.memo.domain.Memo;
import com.project.duo.memo.domain.MemoRequest;
import com.project.duo.memo.domain.User;
import com.project.duo.memo.service.MemoService;
import com.project.duo.memo.service.TodoService;
import com.project.duo.memo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private MemoService memoService;
    private UserService userService;
    private TodoService todoService;

    @Autowired
    public MainController(MemoService memoService, UserService userService, TodoService todoService) {
        this.memoService = memoService;
        this.userService = userService;
        this.todoService = todoService;
    }

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

        userService.save(user1);
        userService.save(user2);

        memoService.save(memo1);
        memoService.save(memo2);
        memoService.save(memo3);
    }

    @GetMapping("/memos/{seq}")
    public ResponseEntity<?> getMemo(@PathVariable String seq){
        Memo memo = memoService.getMemoBySeq(seq);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("memo_preview", "어쩌고 저쩌고 .  ..");
        map.put("user_name", memo.getUser().getUserName());
        map.put("seq", memo.getSeq());
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping("/memos")
    public ResponseEntity<?> saveMemo(@RequestBody MemoRequest memoRequest){

        Map<String, Object> map = new LinkedHashMap<>();

        Memo memo = new Memo();
        User user = userService.getUserById(memoRequest.getId());

        if(user == null){
            logger.info("user null error");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        memo.setUser(user);
        String path = Paths.get("").toAbsolutePath().toString() + "/memo_txt";
        memo.setSeq(String.valueOf(memoRequest.getSeq()));
        memo.setFilePath(path + "/" + memo.getSeq() + ".txt");
        memo.setRegdate(LocalDateTime.now());

        memoService.save(memo);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
