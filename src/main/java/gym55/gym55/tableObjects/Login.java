package gym55.gym55.tableObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    String name;
    String gymPass;
    String role;
    int id;
    String token;

    public Login(User user, String token){
        this.name = user.getFirstName();
        this.gymPass = "2024-11-10";
        this.role = user.getClass_();
        this.id = user.getUserId();
        this.token = token;
    }
}

