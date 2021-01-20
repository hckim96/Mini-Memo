package com.project.duo.memo.repository;

import com.project.duo.memo.domain.Memo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemoRepository {

    @PersistenceContext
    private EntityManager em;

    public Memo findBySeq(String seq){
        return em.createQuery("select m from Memo m where m.seq =" + seq, Memo.class).getSingleResult();
    }

    public Memo save(Memo memo) {
        try{
            em.persist(memo);
        } catch (EntityExistsException e){
            return null;
        }

        return memo;
    }

    public boolean deleteBySeq(String seq) {
        try {
            em.remove(findBySeq(seq));
        } catch (Exception e){
            return false;
        }

        return true;
    }

    public Memo findById(Long id) {
        return em.find(Memo.class, id);
    }

    public boolean deleteById(Long id) {
        try {
            em.remove(findById(id));
        } catch (Exception e){
            return false;
        }

        return true;
    }
}