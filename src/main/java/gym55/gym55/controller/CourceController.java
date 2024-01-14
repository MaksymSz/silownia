package gym55.gym55.controller;

import gym55.gym55.tableObjects.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.EnrollRequest;
import request.NewCourseRequest;
import response.CoursesResponse;
import gym55.gym55.repository.*;
import response.TextResponse;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourceController {
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * Funkcja zwraca wszystkie kursy
     *
     * @return CoursesResponse
     */
    @GetMapping("/courses")
    public CoursesResponse getCourses() {
        CoursesResponse coursesResponse = new CoursesResponse();

        List<Course> courseList = courseRepository.getCourses();

        for (Course c : courseList) {
            coursesResponse.addCourse(c);
        }
        return coursesResponse;
    }

    @CrossOrigin
    @PutMapping("/enroll")
    @ResponseBody
    public ResponseEntity<TextResponse> enrollUser(@RequestBody EnrollRequest enrollRequest) {
        String response = courseRepository.enroll(enrollRequest.getUserId(), enrollRequest.getCourseId());
        return ResponseEntity.ok().body(new TextResponse(response));
    }

    @CrossOrigin
    @PostMapping("/newcourse")
    @ResponseBody
    public ResponseEntity<TextResponse> newCourse(@RequestBody NewCourseRequest newCourseRequest) {
        Course course = courseRepository.addCourse(
                newCourseRequest.getTrainerid(),
                newCourseRequest.getTitle(),
                newCourseRequest.getDescription(),
                newCourseRequest.getWeekday(),
                newCourseRequest.getStartingTime(),
                newCourseRequest.getEndingTime(),
                newCourseRequest.getTrainingRoomid(),
                newCourseRequest.getMaxEnrolled());
        return ResponseEntity.ok().body(new TextResponse("Dodano kurs"));
    }
}
