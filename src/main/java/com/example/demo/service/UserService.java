package com.example.demo.service;

import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

private TokenRepository tokenRepository;
private MailService mailService;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;


  public UserService(TokenRepository tokenRepository, MailService mailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.tokenRepository = tokenRepository;
    this.mailService = mailService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void addUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_USER");
    userRepository.save(user);
    }


private void sendToken(User user){

 String tokenValue= UUID.randomUUID().toString();
  Token token=new Token();
  token.setValue(tokenValue);
  token.setUser(user);
  tokenRepository.save(token);
  String url = "http://localhost:8081/token?value=" + tokenValue;

  try {
    mailService.sendMail(user.getUsername(), "Potwierdzaj to!", url, false);
  } catch (MessagingException e) {
    e.printStackTrace();
  }
}













}
