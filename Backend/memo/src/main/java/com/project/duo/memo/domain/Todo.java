package com.project.duo.memo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Todo {

    public Todo(String task, String seq) {
        this.task = task;
        this.seq = seq;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;

    @Column(unique = true)
    private String seq;

    private String deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user){
        this.user = user;
        user.getTodos().add(this);
    }
}
