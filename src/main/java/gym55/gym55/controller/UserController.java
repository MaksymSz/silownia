package gym55.gym55.controller;

import gym55.gym55.tableObjects.Credentials;
import gym55.gym55.tableObjects.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gym55.gym55.repository.*;
import gym55.gym55.tableObjects.*;
import request.RegisterRequest;
import response.RegisterResponse;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatsRepository statsRepository;

    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    ResponseEntity<Login> login(@RequestBody Credentials credentials) throws NoSuchAlgorithmException {
        String token = this.generateToken(credentials.getEmail());
        User user = userRepository.getUser(userRepository.checkLogin(credentials));
        return ResponseEntity.ok().body(new Login(user, token));
    }

    @CrossOrigin
    @PostMapping("/register")
    @ResponseBody
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) throws NoSuchAlgorithmException {
        User user = userRepository.addUser(registerRequest.getName(), registerRequest.getSurname(), registerRequest.getEmail(), registerRequest.getPassword(), "user");
        String token = this.generateToken(user.getLogin());
        return new RegisterResponse(user.getName(), null, user.getUserId(), user.getClass_(), token);
    }


    private String generateToken(String login) throws NoSuchAlgorithmException {
        long now = System.currentTimeMillis();
        KeyPair keyPair = generateKeyPair();
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 1800*1000))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.PS512).compact();

    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }



}