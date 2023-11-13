package gym55.gym55;

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
    String description;
    String startingTime;
    String endingTime;
    String courseDate;
    int trainingRoomid;
    int maxEnrolled;
    int actEnrolled; //TODO.txt dodać do bazy danych to pole / podobne - ile jeszcze wolnych miejsc.
    int trainerid;
    String name; //TODO.txt masz na mysli nazwe kursu?
    String trainerName;
}

