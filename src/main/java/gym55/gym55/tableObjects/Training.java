package gym55.gym55.tableObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa emulująca jeden wiersz tabeli training z bazy danych
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    String date;
    String time;
}
