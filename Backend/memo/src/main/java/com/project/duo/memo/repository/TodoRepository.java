package com.project.duo.memo.repository;

import com.project.duo.memo.domain.Memo;
import com.project.duo.memo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TodoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Todo> findAll(){
        return em.createQuery("select t from Todo t", Todo.class).getResultList();
    }

    public Todo save(Todo todo) {
        em.persist(todo);
        return todo;
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }
}
