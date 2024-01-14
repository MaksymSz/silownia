package gym55.gym55.tableObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa emulująca jeden wiersz tabeli course z bazy danych
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    int courseId;
    String title;
    String trainer;
    String description;
    String startingtime;
    String endingtime;
    String weekday;
    int trainingroomid;
    int maxenrolled;
    int trainerid;
}

