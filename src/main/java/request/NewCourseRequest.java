package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCourseRequest {
    int trenerId;
    String title;
    String description;
    String date;
    String startingTime;
    String endingTime;
    int trainingRoomid;
    int maxEnrolled;
}
