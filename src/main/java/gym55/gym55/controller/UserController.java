package gym55.gym55.controller;

import gym55.gym55.tableObjects.Credentials;
import gym55.gym55.tableObjects.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.UseCuponRequest;
import gym55.gym55.repository.*;
import gym55.gym55.tableObjects.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    StatsRepository statsRepository;

    @GetMapping("/getuser{id}")
    public User getUser(@PathVariable("id") int id){
        return userRepository.getUser(id);
    }

    @PostMapping("/login")
    @ResponseBody
    ResponseEntity<Login> login(@RequestBody Credentials credentials) throws NoSuchAlgorithmException {
        String token = this.generateToken(credentials.getLogin());
        User user = userRepository.getUser(userRepository.checkLogin(credentials));
        return ResponseEntity.ok().body(new Login(user, token));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<HashMap<Object, Object>> trainings(){
        List<Training> data = statsRepository.getTrainings(2);
        return ResponseEntity.ok().body(new HashMap<>(){{put("data", data);}});}


    @GetMapping("/coupon")
    @ResponseBody
    public Map<String, String> useCupon(UseCuponRequest useCuponRequest){
        //couponRepository.getCoupon(useCuponRequest.getCoupon());
        couponRepository.getCoupon(1);
        // metoda do przedluzania karnetu ???
        Map<String, String> response = new HashMap<>();
        response.put("response", "Zrealizowano");
        return response;
    }





    @PostMapping("/register")
    public void registerUser(){

    //remember to add users to membership
    }


    private String generateToken(String login) throws NoSuchAlgorithmException {
        Long now = System.currentTimeMillis();
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