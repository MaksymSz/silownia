package gym55.gym55.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

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

    public Course getCourse(int courseid){
        return jdbcTemplate.queryForObject(String.format("Select * from course where courseid=%d",courseid),BeanPropertyRowMapper.newInstance(Course.class));
    }

    public String enroll(int userid, int courseid){
        jdbcTemplate.execute(String.format("Insert into enrolled values(%d,%d)",courseid, userid));
        return "Zapisano";
    }

    public List<Enrolled> getEnrolled(int courseid){
        return jdbcTemplate.query(String.format("Select * from enrolled where courseid=%d",courseid), BeanPropertyRowMapper.newInstance(Enrolled.class));
    }
}
