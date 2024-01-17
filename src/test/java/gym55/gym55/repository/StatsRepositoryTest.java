package gym55.gym55.repository;

import gym55.gym55.tableObjects.Training;
import response.GenerateStatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
class StatsRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StatsRepository statsRepository;

    @BeforeEach
    void setUp() {
        // Przygotowanie danych testowych przed każdym testem
        // Na testowej, born boze na produkcyjnej
//        jdbcTemplate.execute("DELETE FROM training");
    }

    @Test
    void testGetPeopleInGym() {
        // Given
        jdbcTemplate.execute("INSERT INTO training (userid, startingtime, trainingdate) VALUES (1, '08:00:00', '2022-01-17')");
        jdbcTemplate.execute("INSERT INTO training (userid, startingtime, trainingdate, endingtime) VALUES (2, '09:00:00', '2022-01-17', '10:00:00')");

        // When
        int peopleInGym = statsRepository.getPeopleInGym();

        // Then
        assertEquals(1, peopleInGym);
        // Dodatkowe
    }

    @Test
    void testGetTrainings() {
        // Given
        jdbcTemplate.execute("INSERT INTO training (userid, startingtime, trainingdate) VALUES (1, '08:00:00', '2022-01-17')");
        jdbcTemplate.execute("INSERT INTO training (userid, startingtime, trainingdate) VALUES (1, '10:00:00', '2022-01-17')");

        // When
        List<Training> trainings = statsRepository.getTrainings(1);

        // Then
        assertNotNull(trainings);
        assertFalse(trainings.isEmpty());
        assertEquals(2, trainings.size());
        // Dodatkowe
    }

    @Test
    void testUserEnters() throws InterruptedException {
        // Given
        int userId = 1;

        // When
        int peopleInGym = statsRepository.getPeopleInGym();
        statsRepository.userEnters(userId);
        int peopleInGym2 = statsRepository.getPeopleInGym();
        // Then
        Thread.sleep(10000);
        assertEquals(peopleInGym+1, peopleInGym2);
    }

    @Test
    void testUserExits() throws InterruptedException {
        // Given
        int userId = 1;

        // When
        int peopleInGym = statsRepository.getPeopleInGym();
        statsRepository.userExits(userId);
        int peopleInGym2 = statsRepository.getPeopleInGym();
        // Then
        Thread.sleep(10000);
        assertEquals(peopleInGym-1, peopleInGym2);
        // Dodatkowe testy
    }

    @Test
    void testGenerate() {
        // Given
        String fromDate = "2022-01-01";
        String toDate = "2022-12-31";

        // When
        GenerateStatsResponse statsResponse = statsRepository.generate(fromDate, toDate);

        // Then
        assertNotNull(statsResponse);
        // Dodatkowe testy
    }
}
