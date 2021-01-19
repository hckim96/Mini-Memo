package com.project.duo.memo.service;

import com.project.duo.memo.domain.Memo;
import com.project.duo.memo.domain.MemoRequest;
import com.project.duo.memo.domain.User;
import com.project.duo.memo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
