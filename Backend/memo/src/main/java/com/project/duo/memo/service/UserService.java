package com.project.duo.memo.service;

import com.project.duo.memo.domain.User;
import com.project.duo.memo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean signInUser(User user) {
        User byUsername = userRepository.findByUsername(user.getUserName());

        if(byUsername == null || !byUsername.getUserPassword().equals(/*encoder.encode*/(user.getUserPassword()))){
            return false;
        }

        return true;
    }
}
