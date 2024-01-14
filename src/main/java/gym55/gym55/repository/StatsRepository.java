package gym55.gym55.repository;

import gym55.gym55.tableObjects.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import request.GenerateStatsRequest;
import response.GenerateStatsResponse;

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
        return jdbcTemplate.queryForObject("Select count(*) from training where endingtime is null", Integer.class);
    }

    /**
     * Funkcja wyszukująca wszystkie treningi użytkownika w bazie danych po id
     * @param userid id użytkownika
     * @return listę obiektów Training, każdy oznaczający jeden trening użytkownika
     */
    //TODO.txt Dorobić ograniczenie aby zwracał tylko treningi z ostatnich 7 dni i sortował, pierwsy rekord ma mieć najstarszą datę. ewentualnie zakres dat.
    //  Też może zmiana na froncie, aby podawać zakres dat? jak nie, to tylko te 7 dni zmienić w SQL.
    //zmienione na 365 dni
    public List<Training> getTrainings(int userid){
        return jdbcTemplate.query(String.format("Select trainingdate as date, endingtime - startingtime as time from training " +
                "where userid=%d and trainingdate > current_date - interval '365 days' order by trainingdate desc", userid),BeanPropertyRowMapper.newInstance(Training.class));
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

    public GenerateStatsResponse generate(String from, String to){
        return jdbcTemplate.queryForObject(String.format("Select count(userid)/(date_part('day', '%s'::date) \n" +
                "- date_part('day', '%s'::date)) as avgusers, \n" +
                "avg(endingtime - startingtime) as avgtime, count(distinct userid) as users,\n" +
                "(Select count(userid) from \"user\" where registrationdate between '%s' and '%s') as newusers\n" +
                "from training \n" +
                "where trainingdate between '%s' and '%s';", to, from, from, to, from, to), BeanPropertyRowMapper.newInstance(GenerateStatsResponse.class));
    }
}
