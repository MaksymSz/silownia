package gym55.gym55.controller;

import gym55.gym55.repository.CouponRepository;
import gym55.gym55.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.UseCuponRequest;
import response.TextResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CouponController {
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/coupon")
    @ResponseBody
    public ResponseEntity<TextResponse> useCoupon(@RequestBody UseCuponRequest useCuponRequest){
        int check = couponRepository.checkCoupon(useCuponRequest.getCoupon());
        String response;

        if(check >= 1){
            userRepository.extendMembership(useCuponRequest.getId());
            response = "Zrealizowano";}
        else{
            response = "Zły kod kuponu";
        }
        return ResponseEntity.ok().body(new TextResponse(response));
    }
}
