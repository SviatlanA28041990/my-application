package by.khatkevich.service;

import by.khatkevich.entity.User;
import by.khatkevich.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserPage userPage){
        User user = new User();
        user.setUsername(userPage.getUsername());
        user.setPassword(passwordEncoder.encode(userPage.getPassword()));
        userRepository.save(user);
    }
}
