package gym55.gym55.repository;

import gym55.gym55.tableObjects.Report;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
class ReportRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReportRepository reportRepository;

    @BeforeEach
    void setUp() {
        // Przygotowanie danych testowych przed każdym testem
        // zakladajac ze mamy baze danych przygotowana do testow i nie jest to baza produkcyjna
//        jdbcTemplate.execute("DELETE FROM report");
    }

    @Test
    void testGetReport() {
        // Given
        int reportId = 1;

        jdbcTemplate.execute("INSERT INTO report (reportid, description, reportdate) " +
                "VALUES (1, 'TestDescription', '2022-01-17')");

        // When
        Report report = reportRepository.getReport(reportId);

        // Then
        assertNotNull(report);
        // Dodatkowe
    }

    @Test
    void testAddReport() {
        // Given
        String title = "TestTitle";
        String description = "TestDescription";
        String date = LocalDate.now().toString();

        // When
        Report addedReport = reportRepository.addReport(title, description, date);

        // Then
        assertNotNull(addedReport);
        // Dodatkowe
    }
}
