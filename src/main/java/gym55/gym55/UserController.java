package gym55.gym55;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public ResponseEntity<String> getUsers() {
        String query = "SELECT COUNT(*) FROM user";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return new ResponseEntity<>("Total users: " + count, HttpStatus.OK);
    }
}