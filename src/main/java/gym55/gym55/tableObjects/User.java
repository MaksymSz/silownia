package gym55.gym55.tableObjects;

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

    String firstName;
    String isValid;
    int userId;
    String class_;
    String token;
    String lastName;
    String email;
    String login;
    String password;
    //TODO.txt pole w bazie danych do usera, termin ważności karnetu
    //TODO.txt metoda do przedłużania karnetu (kupon / płatność)
    public String getName() {
        return firstName + " " + lastName;
    }
}
