package gym55.gym55.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    int courseid;
    String description;
    String startingTime;
    String endingTime;
    String courseDate;
    int trainingRoomid;
    int maxEnrolled;
    int trainerid;
    String name;
}

