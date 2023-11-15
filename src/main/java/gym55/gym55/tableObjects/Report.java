package gym55.gym55.tableObjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa emulująca jeden wiersz tabeli report z bazy danych
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Report {
    int reportid;
    String description;
    String reportdate;
}
