package gym55.gym55.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Report getReport(int reportid){
        return jdbcTemplate.queryForObject(String.format("Select * from report where reportid = %d",reportid), BeanPropertyRowMapper.newInstance(Report.class));
    }

    public Report addReport(String description, String date){
        jdbcTemplate.execute(String.format("Insert into report (description, date) " +
                "values (%s,%s)",description,date));
        int id = jdbcTemplate.queryForObject("Select max(reportid) from report",BeanPropertyRowMapper.newInstance(int.class));
        return getReport(id);
    }
}
