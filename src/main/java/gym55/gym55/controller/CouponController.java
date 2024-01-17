package gym55.gym55.controller;
/**
 * Pakiet kontrolujący endpointy
 */
import gym55.gym55.repository.CouponRepository;
import gym55.gym55.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.UseCuponRequest;
import response.TextResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa zawierająca endpointy związane z zarządzaniem kuponami
 */
@RestController
public class CouponController {
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * Endpoint, który symuluje użycie kuponu
     * @param useCuponRequest dane kuponu wpisane przez użytkownika
     * @return Odpowiedź odnośnie sukcesu bądź braku sukcesu operacji
     */
    @CrossOrigin
    @PostMapping("/coupon")
    @ResponseBody
    public ResponseEntity<TextResponse> useCoupon(@RequestBody UseCuponRequest useCuponRequest){
        int check = couponRepository.checkCoupon(useCuponRequest.getCoupon());
        String response;

        if(check >= 1){
            userRepository.extendMembership(useCuponRequest.getId());
            response = "2024-12-10";}
        else{
            response = "Zły kod kuponu";
        }
        return ResponseEntity.ok().body(new TextResponse(response));
    }
}
