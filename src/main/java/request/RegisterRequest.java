package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca requesty rest api odnośnie rejestracji
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String email;
    String password;
    String name;
    String surname;
}
