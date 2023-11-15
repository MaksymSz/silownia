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
    String description;
    String startingTime;
    String endingTime;
    String courseDate;
    int trainingRoomid;
    int maxEnrolled;
    int actEnrolled; //TODO: zmienić strukturę zapytań z CourseRepository
    int trainerId;
    String name;
    String trainerName;
}

