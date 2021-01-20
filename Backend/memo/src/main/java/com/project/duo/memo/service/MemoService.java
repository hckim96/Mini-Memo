package com.project.duo.memo.service;

import com.project.duo.memo.domain.Memo;
import com.project.duo.memo.domain.MemoRequest;
import com.project.duo.memo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@Transactional
public class MemoService {
    private MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo getMemoBySeq(String seq) {
        return (Memo) memoRepository.findBySeq(seq);
    }

    public Memo save(Memo memo) {
        return memoRepository.save(memo);
    }

    public boolean deleteBySeq(String seq) {
        Memo memo = memoRepository.findBySeq(seq);
        String path = memo.getFilePath();
        try{
            File file = new File(path);
            file.delete();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }

        return memoRepository.deleteBySeq(seq);
    }

    public void modifyMemo(Memo memo, String content) {
        String path = memo.getFilePath();
        FileWriter fileWriter = null;
        File file;

        try{
            file = new File(memo.getFilePath());
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void save(Memo memo, MemoRequest memoRequest) {
//        String path = Paths.get("").toAbsolutePath().toString() + "/memo_txt";
        memo.setSeq(String.valueOf(memoRequest.getSeq()));
        memo.setRegdate(LocalDateTime.now());

        String path = Paths.get("").toAbsolutePath().toString();
        String directoryName = path.concat("/texts");
        String fileName = memoRequest.getId() + "_" + memo.getSeq() + ".txt";
        memo.setFilePath(directoryName + "/" + fileName);
        save(memo);

        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }

        File file = new File(directoryName + "/" + fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(memoRequest.getContent());
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
