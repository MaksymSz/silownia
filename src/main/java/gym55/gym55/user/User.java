package gym55.gym55.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int userid;
    String firstName;
    String lastName;
    String email;
    String class_;
    String isValid;
}
