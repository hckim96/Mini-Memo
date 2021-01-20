package com.project.duo.memo.repository;

import com.project.duo.memo.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;


    public User findById(Long id){
        System.out.println("==================== " + id);
        return em.find(User.class, id);
    }

    public User save(User user) {
        try{
            em.persist(user);
        } catch (EntityExistsException e){
            return null;
        }

        return user;
    }
}
