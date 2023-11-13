package gym55.gym55.controller;

import gym55.gym55.CouponRepository;
import gym55.gym55.User;
import gym55.gym55.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.UseCuponRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CouponRepository couponRepository;

    @GetMapping("/getuser{id}")
    public User getUser(@PathVariable("id") int id){
        return userRepository.getUser(id);
    }

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

    @GetMapping("/test")
    @ResponseBody
    public Map<String, Integer> test(){
        Map<String, Integer> response = new HashMap<>();
        response.put("id", 1);
        return response;
    }




    @GetMapping("/register")
    public void registerUser(){

    }


}