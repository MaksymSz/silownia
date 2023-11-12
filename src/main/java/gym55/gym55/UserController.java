package gym55.gym55;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getuser{id}")
        public User getUser(@PathVariable("id") int id){
            return userRepository.getUser(id);
        }



}