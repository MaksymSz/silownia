package gym55.gym55;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Klasa emulująca jeden wiersz tabeli enrolled z bazy danych
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrolled {
    int userid;
    int trainingroomid;
}
