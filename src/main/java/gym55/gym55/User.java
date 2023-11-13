package gym55.gym55;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa emulująca jeden wiersz tabeli user z bazy danych
 */
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

    public String getName() {
        return firstName + " " + lastName;
    }
}
