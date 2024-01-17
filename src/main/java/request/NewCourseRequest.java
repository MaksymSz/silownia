package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Klasa obsługująca requesty rest api odnośnie dodania kursu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCourseRequest {
    String title;
    String weekday;
    String description;
    String startingTime;
    String endingTime;
    int trainingRoomid;
    int maxEnrolled;
    int id;
}
