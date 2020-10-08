package com.example.demo.security;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

@Autowired
    public Start(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        User userJanusz = new User();
        userJanusz.setName("j");
        userJanusz.setPassword(passwordEncoder.encode("j"));
        userJanusz.setRole("ROLE_ADMIN");
        userJanusz.setEnabled(true);


        User userBogdan = new User();
        userBogdan.setName("b");
        userBogdan.setPassword(passwordEncoder.encode("b"));
        userBogdan.setRole("ROLE_USER");
        userBogdan.setEnabled(true);

        userRepository.save(userJanusz);
        userRepository.save(userBogdan);



    }

}
