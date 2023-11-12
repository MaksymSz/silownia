package gym55.gym55.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getPeopleInGym(){
        return jdbcTemplate.queryForObject("Select count(*) from trainings where endingtime is null", BeanPropertyRowMapper.newInstance(int.class));
    }

    public List<Training> getTrainings(int userid){
        return jdbcTemplate.query(String.format("Select startingtime,endingtime,trainingdate from training " +
                "where userid=%d ", userid),BeanPropertyRowMapper.newInstance(Training.class));
    }

    public void userEnters(int userid){
        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalTime.now().toString();
        jdbcTemplate.execute(String.format("Insert into training (userid,startingtime,trainingdate) " +
                "values (%d,%s,%s)",userid,time,date));
    }

    public void userExits(int userid){
        String time = java.time.LocalTime.now().toString();
        jdbcTemplate.execute(String.format("insert into training (endingtime) values (%s)" +
                "where userid=%d and endingtime is null",time, userid));
    }
}
