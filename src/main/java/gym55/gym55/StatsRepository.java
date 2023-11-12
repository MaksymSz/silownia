package gym55.gym55;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Klasa obsługująca operacje na bazie danych związane z tabelą training
 */
@Repository
public class StatsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Funkcja informująca o obecnej liście osób na siłowni
     * @return liczba osób na siłowni w postaci obiektu int
     */
    public int getPeopleInGym(){
        return jdbcTemplate.queryForObject("Select count(*) from training where endingtime is null", BeanPropertyRowMapper.newInstance(int.class));
    }

    /**
     * Funkcja wyszukująca wszystkie treningi użytkownika w bazie danych po id
     * @param userid id użytkownika
     * @return listę obiektów Training, każdy oznaczający jeden trening użytkownika
     */
    public List<Training> getTrainings(int userid){
        return jdbcTemplate.query(String.format("Select startingtime,endingtime,trainingdate from training " +
                "where userid=%d ", userid),BeanPropertyRowMapper.newInstance(Training.class));
    }

    /**
     * Funkcja zapisująca w bazie danych datę i czas wejścia użytkoniwka na siłownię
     * @param userid id użytkownika
     */
    public void userEnters(int userid){
        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalTime.now().toString();
        jdbcTemplate.execute(String.format("Insert into training (userid,startingtime,trainingdate) " +
                "values (%d,%s,%s)",userid,time,date));
    }

    /**
     * Funkcja zapisująca w bazie danych czas wyjścia użytkownika z siłowni
     * @param userid id użytkownika
     */
    public void userExits(int userid){
        String time = java.time.LocalTime.now().toString();
        jdbcTemplate.execute(String.format("insert into training (endingtime) values (%s)" +
                "where userid=%d and endingtime is null",time, userid));
    }
}
