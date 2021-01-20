package com.project.duo.memo.controller;

import com.project.duo.memo.domain.ErrorResponse;
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
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.RollbackException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MemoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MemoService memoService;
    private final UserService userService;

    @Autowired
    public MemoController(MemoService memoService, UserService userService, TodoService todoService) {
        this.memoService = memoService;
        this.userService = userService;
    }

    @PostConstruct
    public void testInit(){

    }

    @GetMapping("/memos/{id}")
    public ResponseEntity<?> getMemo(@PathVariable Long id){
        Map<String, Object> map = new LinkedHashMap<>();
        Memo memo = memoService.getMemoById(id);

        if(memo == null){
            return new ResponseEntity<>(new ErrorResponse("Memo Not Found").getError(), HttpStatus.BAD_REQUEST);
        }

        map.put("memo_file_path", memo.getFilePath());
        map.put("user_name", memo.getUser().getUserName());
        map.put("seq", memo.getSeq());
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping("/memos")
    public ResponseEntity<?> saveMemo(@RequestBody MemoRequest memoRequest){
        Map<String, Object> map = new LinkedHashMap<>();
        Memo memo = new Memo();
        User user = userService.getUserById(memoRequest.getMemberId());

        if(user == null){
            logger.info("user null error");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        memo.setUser(user);
        memoService.save(memo, memoRequest);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/memos/{id}")
    public ResponseEntity<?> deleteMemo(@PathVariable Long id){
        Map<String, Object> map = new LinkedHashMap<>();

        try{
            memoService.deleteById(id);
        } catch (UnexpectedRollbackException re){
            return new ResponseEntity<>(new ErrorResponse("exception"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/memos")
    public ResponseEntity<?> putMemo(@RequestBody MemoRequest memoRequest){
        Map<String, Object> map = new LinkedHashMap<>();

        Memo memo = memoService.getMemoById(memoRequest.getMemoId());
        memoService.modifyMemo(memo, memoRequest.getContent());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
