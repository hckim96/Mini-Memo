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

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

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

        return byUsername != null && byUsername.getUserPassword().equals(/*encoder.encode*/(user.getUserPassword()));
    }

    public boolean checkUserNameDuplicated(String username) {
        return userRepository.findByUsername(username) == null;
    }

    public boolean signUpUser(User user) {
        return userRepository.save(user) != null;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
