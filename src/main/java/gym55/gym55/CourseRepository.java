package gym55.gym55;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public Course addCourse(int trainerid, String name, String description, String date, String startingTime, String endingTime, int trainingRoomid, int maxEnrolled) throws RuntimeException {
        try{
            jdbcTemplate.queryForObject(String.format("Select u.userid from \"user\" u where u.userid = %d", trainerid), BeanPropertyRowMapper.newInstance(int.class));
            jdbcTemplate.queryForObject(String.format("Select t.trainingroomid from trainingroom t where t.trainingroomid = %d", trainingRoomid), BeanPropertyRowMapper.newInstance(int.class));}
        catch(Exception IncorrectResultSizeDataAccessException){
            return new Course();
        }
        jdbcTemplate.execute(String.format("Insert into course(trainerid, name, description, coursedate, startingtime, endingtime, trainingroomid, maxenrolled)" +
                "values (%d,%s,%s,%s,%s,%s,%d,%d)", trainerid, name, description, date, startingTime, endingTime, trainingRoomid, maxEnrolled));
        int id = jdbcTemplate.queryForObject("Select max(courseid) from course", BeanPropertyRowMapper.newInstance(int.class));
        return getCourse(id);
    }

    /**
     * Funkcja pobierają wiersz tabeli course z bazy danych w postaci obiektu klasy Course
     * @param courseid id zajęć
     * @return szukane zajęcia w postaci obiektu klasy Course
     */
    public Course getCourse(int courseid){
        return jdbcTemplate.queryForObject(String.format("Select * from course where courseid=%d",courseid),BeanPropertyRowMapper.newInstance(Course.class));
    }

    /**
     * Funkcja zapisująca klienta na zajęcia
     * @param userid id klienta
     * @param courseid id zajęć
     * @throws RuntimeException jeśli liczba zapisanych uczestników jest równa maksymalnej możliwej na te zajęcia
     * @return łańcuch znaków "Zapisano" gdy zapisanie się powiedzie
     */
    public String enroll(int userid, int courseid){
        //TODO check if enrolled != maxEnrolled
        jdbcTemplate.execute(String.format("Insert into enrolled values(%d,%d)",courseid, userid));
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