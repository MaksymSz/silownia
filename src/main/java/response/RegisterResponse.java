package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca odpowiedzi backendu na requesty rest api dotyczące rejestracji
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    String email;
    String password;
    String name;
    String surname;
}
