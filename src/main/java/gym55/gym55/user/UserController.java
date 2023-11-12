package gym55.gym55.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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