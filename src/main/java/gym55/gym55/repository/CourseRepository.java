package gym55.gym55.repository;

import gym55.gym55.tableObjects.Course;
import gym55.gym55.tableObjects.Enrolled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import response.CoursesResponse;

import java.net.http.HttpResponse;
import java.util.List;

/**
 * Klasa obsługująca operacje na bazie danych związane z tabelą course
 */
@Repository
public class CourseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Funkcja dodająca zajęcia do bazy danych
     * @param trainerid id trenera
     * @param name nazwa zajęć
     * @param description opis zajęć
     * @param date data
     * @param startingTime czas rozpoczęcia
     * @param endingTime czas zakończenia
     * @param trainingRoomid id sali
     * @param maxEnrolled maksymalna liczba uczestników
     * @return stworzony wiersz tabeli course w postaci obiektu klasy Course
     * @throws RuntimeException pusty obiekt klasy Course jeśli wybrana sala lub trener nie istnieje
     */
    //TODO.txt dodaj aby zwracało również imie trenera - łatiwej w zapytaniu sql to zrobić, za pomocą joina niż w backendzie wywoływać kolejne zapytanie typu getUser(id)
    //TODO.txt czy potrzebujemy zwracać kurs? nie wystarczy 0/1 czy się udało czy nie?
    //TODO.txt zamiana ciągu zmiennych na obiekt klasy NewCourseRequest? Patrz CourseController / NewCourseRequest.
    public Course addCourse(int trainerid, String name, String description, String date, String startingTime, String endingTime, int trainingRoomid, int maxEnrolled) throws RuntimeException {
        int id = jdbcTemplate.queryForObject("Select max(courseid) from course", Integer.class);
        jdbcTemplate.execute(String.format("Insert into course(courseid, trainerid, name, description, weekday, startingtime, endingtime,trainingroomid, maxenrolled)" +
                "values (%d,%d,'%s','%s','%s','%s','%s',1,%d)", id+1 ,trainerid, name, description, date, "11:11", "11:11", maxEnrolled));

        return new Course();
    }

    /**
     * Funkcja pobierająca wiersz tabeli course z bazy danych w postaci obiektu klasy Course
     * @param courseid id zajęć
     * @return szukane zajęcia w postaci obiektu klasy Course
     */
    //TODO.txt Przetestować czy działa / jak po dodaniu nowych pól.
    public Course getCourse(int courseid){
        return jdbcTemplate.queryForObject(String.format(
                "Select courseid, name, description, startingtime, endingtime, coursedate, trainingroomid, maxenrolled, trainerid," +
                        "(select count(*) from enrolled where courseid = %d) as actEnrolled," +
                        "u.firstname as trainerName" +
                        "from course" +
                        "left join user u using(userid)" +
                        "where courseid = %d;",
                courseid, courseid),BeanPropertyRowMapper.newInstance(Course.class));
    }

    /**
     * MS
     * Funkcja dodana w celu zwrocenia wszystkich.
     * @return wszystkie zajecia
     */
    public List<Course> getCourses(){
        return jdbcTemplate.query("Select c.courseid, c.name as title, c.description, concat(u.firstname,' ', u.lastname) as trainer from course c left join \"user\" u on c.trainerid = u.userid;",BeanPropertyRowMapper.newInstance(Course.class));
    }

    /**
     * Funkcja zapisująca klienta na zajęcia
     * @param userid id klienta
     * @param courseid id zajęć
     * @throws RuntimeException jeśli liczba zapisanych uczestników jest równa maksymalnej możliwej na te zajęcia
     * @return łańcuch znaków "Zapisano" gdy zapisanie się powiedzie
     */
    public String enroll(int userid, int courseid){
        int maxenrolled = jdbcTemplate.queryForObject(String.format("Select maxenrolled from course where courseid = 1;", courseid), Integer.class);
        int enrolled = jdbcTemplate.queryForObject(String.format("Select count(userid) from enrolled where courseid = %d;", courseid), Integer.class);
        if(enrolled == maxenrolled){
            return "Brak miejsc";
        }
        jdbcTemplate.execute(String.format("Insert into enrolled values(%d, %d)",courseid, userid));
        return "Zapisano";
    }

    /**
     * Funkcja zwracająca listę osób zapisanych na zajęcia
     * @param courseid id zajęć
     * @return lista obiektów klasy Enrolled
     */
    public List<Enrolled> getEnrolled(int courseid){
        return jdbcTemplate.query(String.format("Select * from enrolled where courseid=%d",courseid), BeanPropertyRowMapper.newInstance(Enrolled.class));
    }
}
