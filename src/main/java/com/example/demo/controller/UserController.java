package com.example.demo.controller;

import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {


    private UserService userService;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    public UserController() {
    }


    public UserController(UserService userService, UserRepository userRepository, TokenRepository tokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }


    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        //pobiera role
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authorities", authorities);
        return "hello";
    }


    @GetMapping("/sing-up")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "sing-up";
    }


    @PostMapping("/register")
    public String register(User user) {
        userService.addUser(user);
        return "sing-up";
    }


    @GetMapping("/token")
    public String signup(@RequestParam String value) {
        Token byValue = tokenRepository.findByValue(value).get();
        User user = byValue.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "hello";
    }


}
