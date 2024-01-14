package response;
import gym55.gym55.tableObjects.Course;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * Klasa przechowująca odpowiedz na /courses
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesResponse {
    List<UnitCourseResponse> courses = new ArrayList<>();
    public void addCourse(Course course){
        courses.add(new UnitCourseResponse(course.getCourseId(), course.getTitle(), course.getTrainer(), course.getDescription()));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UnitCourseResponse {
    int courseID;
    String title;
    String trainer;
    String description;
}
