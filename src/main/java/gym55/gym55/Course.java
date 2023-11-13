package gym55.gym55;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    int actEnrolled;
    int trainerid;
    String name; // masz na mysli nazwe kursu?
    String trainerName;
}

