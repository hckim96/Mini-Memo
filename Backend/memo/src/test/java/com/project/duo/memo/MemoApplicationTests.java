package com.project.duo.memo;

import com.project.duo.memo.domain.Memo;
import com.project.duo.memo.domain.Todo;
import com.project.duo.memo.domain.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class MemoApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private EntityManager em;

	@Autowired
	public MemoApplicationTests(EntityManager em) {
		this.em = em;
	}

	@Test
	void contextLoads() {
	}

	@Test
	@Rollback(value = false)
	@Transactional
	void ERDtest(){
		User user1 = new User("kim", "password kim");
		User user2 = new User("lee", "password lee");


		Memo memo1 = new Memo("메모 1", "3");
		memo1.setUser(user1);
		Memo memo2 = new Memo("메모 2", "5");
		memo2.setUser(user1);
		Memo memo3 = new Memo("메모 3", "1");
		memo3.setUser(user2);

		Todo todo1 = new Todo("todo 1", "3");
		todo1.setUser(user2);
		Todo todo2 = new Todo("todo 2", "5");
		todo2.setUser(user2);
		Todo todo3 = new Todo("todo 3", "15");
		todo3.setUser(user1);

		em.persist(memo1);
		em.persist(memo2);
		em.persist(memo3);

		em.persist(todo1);
		em.persist(todo2);
		em.persist(todo3);

		List<Todo> todos = user1.getTodos();

	}



}
