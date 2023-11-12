package gym55.gym55;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Klasa obsługująca operacje na bazie danych związane z tabelą report
 */
@Repository
public class ReportRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Funkcja wyszukująca zgłoszenie w bazie danych
     * @param reportid id zgłoszenia
     * @return zgłoszenie w postaci obiektu klasy Report
     */
    public Report getReport(int reportid){
        return jdbcTemplate.queryForObject(String.format("Select * from report where reportid = %d",reportid), BeanPropertyRowMapper.newInstance(Report.class));
    }

    /**
     * Funkcja dodająca zgłoszenie do bazy danych
     * @param description opis zgłoszenia
     * @param date data zgłoszenia
     * @return dodany wiersz w postaci obiektu klasy Report
     */
    public Report addReport(String description, String date){
        jdbcTemplate.execute(String.format("Insert into report (description, date) " +
                "values (%s,%s)",description,date));
        int id = jdbcTemplate.queryForObject("Select max(reportid) from report",BeanPropertyRowMapper.newInstance(int.class));
        return getReport(id);
    }
}
