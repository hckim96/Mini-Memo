package com.project.duo.memo.repository;

import com.project.duo.memo.domain.Memo;
import com.project.duo.memo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
