package gym55.gym55.repository;

import gym55.gym55.tableObjects.Course;
import gym55.gym55.tableObjects.Enrolled;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
class CourseRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        // Przygotowanie danych testowych przed każdym testem, w zalozeniu ze testujemy na bazie innej niz produkcyjna
//        jdbcTemplate.execute("DELETE FROM enrolled");
//        jdbcTemplate.execute("DELETE FROM course");
//        jdbcTemplate.execute("INSERT INTO \"user\" (userid, firstname, lastname) VALUES (1, 'John', 'Doe')");
    }

    @Test
    void testAddCourse() {
        // Given
        int trainerId = 1;
        String courseName = "TestCourse";
        String description = "TestDescription";
        LocalDate date = LocalDate.now();
        LocalTime startingTime = LocalTime.of(10, 0);
        LocalTime endingTime = LocalTime.of(12, 0);
        int trainingRoomId = 1;
        int maxEnrolled = 10;

        // When
        Course addedCourse = courseRepository.addCourse(trainerId, courseName, description, date.toString(), startingTime.toString(), endingTime.toString(), trainingRoomId, maxEnrolled);

        // Then
        assertNotNull(addedCourse);
    }

    @Test
    void testGetCourse() {
        // Given
        int courseId = 1;

        jdbcTemplate.execute("INSERT INTO course (courseid, trainerid, name, description, weekday, startingtime, endingtime, trainingroomid, maxenrolled) " +
                "VALUES (1, 1, 'TestCourse', 'TestDescription', 'Monday', '10:00', '12:00', 1, 10)");

        // When
        Course course = courseRepository.getCourse(courseId);

        // Then
        assertNotNull(course);
        // dodatkowe sprawdzanie
    }

    @Test
    void testEnroll() {
        // Given
        int userId = 1;
        int courseId = 1;

        jdbcTemplate.execute("INSERT INTO course (courseid, trainerid, name, description, weekday, startingtime, endingtime, trainingroomid, maxenrolled) " +
                "VALUES (1, 1, 'TestCourse', 'TestDescription', 'Monday', '10:00', '12:00', 1, 1)");

        // When
        String result = courseRepository.enroll(userId, courseId);

        // Then
        assertEquals("Zapisano", result);
    }

    @Test
    void testGetEnrolled() {
        // Given
        int courseId = 1;
        int userId = 1;

        jdbcTemplate.execute("INSERT INTO enrolled (courseid, userid) VALUES (1, 1)");

        // When
        List<Enrolled> enrolledList = courseRepository.getEnrolled(courseId);

        // Then
        assertNotNull(enrolledList);
        assertFalse(enrolledList.isEmpty());
        assertEquals(1, enrolledList.size());
    }

}