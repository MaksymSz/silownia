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
import request.QrRequest;
import request.RegisterRequest;
import response.QrResponse;
import response.RegisterResponse;
import java.util.Random;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Klasa obsługująca endpointy związane z użytkownikiem
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatsRepository statsRepository;

    /**
     * Metoda pozwalająca użytkownikowi na zalogowanie
     * @param credentials dane logowania
     * @return Informacje o użytkowniku
     * @throws NoSuchAlgorithmException
     */
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    ResponseEntity<Login> login(@RequestBody Credentials credentials) throws NoSuchAlgorithmException {
        String token = generateToken(credentials.getEmail());
        User user = userRepository.getUser(userRepository.checkLogin(credentials));
        return ResponseEntity.ok().body(new Login(user, token));
    }

    /**
     * Endpoint do
     * @param registerRequest dane o użytkowniku
     * @return obiekt klasy RegisterResponse
     * @throws NoSuchAlgorithmException
     */
    @CrossOrigin
    @PostMapping("/register")
    @ResponseBody
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) throws NoSuchAlgorithmException {
        User user = userRepository.addUser(registerRequest.getName(), registerRequest.getSurname(), registerRequest.getEmail(), registerRequest.getPassword(), "user");
        String token = this.generateToken(registerRequest.getEmail());
        return new RegisterResponse(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getName(), registerRequest.getSurname());
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

    /**
     * Endpoint, który generuje nowy kod qr
     * @param qrRequest id użytkownika w postaci obiektu klasy qrRequest
     * @return Obiekt klasy QrResponse
     * @throws NoSuchAlgorithmException
     */
    @CrossOrigin
    @PostMapping("/newqr")
    @ResponseBody
    ResponseEntity<QrResponse> login(@RequestBody QrRequest qrRequest) throws NoSuchAlgorithmException {
        Random rand = new Random();
        String s = String.valueOf(rand.nextInt(100));
        return ResponseEntity.ok().body(new QrResponse(s));
    }

    /**
     * Endpoint, który przekazuje do frontendu kod qr użytkownika
     * @param id
     * @return kod pozwalający na wygenerowanie kodu qr przez frontend
     */
    @GetMapping("/qr/{id}")
    public ResponseEntity<QrResponse> getDashboard(@PathVariable("id") int id) {
        Random rand = new Random();
        String s = String.valueOf(rand.nextInt(100));
        return ResponseEntity.ok().body(new QrResponse(s));
    }
}