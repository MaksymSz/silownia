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
    int userid;
    String firstName;
    String lastName;
    String email;
    String class_;
    String isValid;
    //TODO.txt pole w bazie danych do usera, termin ważności karnetu
    //TODO.txt metoda do przedłużania karnetu (kupon / płatność)
    public String getName() {
        return firstName + " " + lastName;
    }
}
