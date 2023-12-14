package gym55.gym55.controller;

import gym55.gym55.tableObjects.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.EnrollRequest;
import request.NewCourseRequest;
import response.CoursesResponse;
import gym55.gym55.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourceController {
    @Autowired
    CouponRepository couponRepository; // ???
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
    public CoursesResponse getCourses(){
        CoursesResponse coursesResponse = new CoursesResponse();

        // prawidlowo
        //List<Course> courseList = courseRepository.getCourses();

        //testowo
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "Yoga", "Kurs podstawowy zajęć yoga.", "10:00", "12:00", "2023-01-07", 12, 20, 0, 5,"Magda"));
        courseList.add(new Course(2, "Pilates", "Kurs podstawowy pilatesu.", "12:00", "14:00", "2023-01-08", 11, 20, 0, 7,"Ala"));
        for(Course c : courseList){
            coursesResponse.addCourse(c);
        }
        return coursesResponse;
    }

    @GetMapping("/enroll")
    public Map<String, String> enrollUser(EnrollRequest enrollRequest){

        Map<String, String> response = new HashMap<>();
        response.put("response", "Osiagnieto Limit");
        //response.put("response", "Zapisano");
        return response;

        /*
        int userId = enrollRequest.getUserId();
        int courseId = enrollRequest.getCourseId();

        Map<String, String> response = new HashMap<>();
        Course course = courseRepository.getCourse(courseId);

        if(course.getActEnrolled() < course.getMaxEnrolled()) {
            response.put("response", courseRepository.enroll(userId, courseId));
        } else {
            response.put("response", "Osiagnieto Limit");
        }
        return response;
        */

    }

    @GetMapping("/newcourse")
    @ResponseBody
    public Map<String, String> newcCurse(NewCourseRequest newCourseRequest){

        Map<String, String> response = new HashMap<>();

        // dodac obsluge gdyby cos wywalilo???
        Course course =  courseRepository.addCourse(
                newCourseRequest.getTrenerId(),
                newCourseRequest.getTitle(),
                newCourseRequest.getDescription(),
                newCourseRequest.getDate(),
                newCourseRequest.getStartingTime(),
                newCourseRequest.getEndingTime(),
                newCourseRequest.getTrainingRoomid(),
                newCourseRequest.getMaxEnrolled());
        if(!course.equals(new Course())){
            response.put("response", "Dodano");
        } else{
            response.put("response", "Nie udalo sie");
        }
        return response;
    }

    @GetMapping("/enroll1")
    @ResponseBody
    public Boolean enroll(){return true;}

}
