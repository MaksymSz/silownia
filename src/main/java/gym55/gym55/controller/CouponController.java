package gym55.gym55.controller;

import gym55.gym55.repository.CouponRepository;
import gym55.gym55.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.UseCuponRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CouponController {
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/coupon")
    @ResponseBody
    //todo jak odpowiadać?
    public Map<String, String> useCoupon(@RequestBody UseCuponRequest useCuponRequest){
        int check = couponRepository.checkCoupon(useCuponRequest.getCoupon());

        Map<String, String> response = new HashMap<>();
        if(check >= 1){
            userRepository.extendMembership(useCuponRequest.getUserId());
            response.put("response", "Zrealizowano");}
        else{
            response.put("response", "Błędny kod kuponu");
        }
        return response;
    }
}
