package gym55.gym55.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

// Dodane importy
import gym55.gym55.tableObjects.Credentials;
import gym55.gym55.tableObjects.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetUser() {
        // Given
        jdbcTemplate.execute("INSERT INTO \"user\" (userid, firstname, class_) VALUES (1, 'John', 'user')");

        // When
        User user = userRepository.getUser(1);

        // Then
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("2024-11-10", user.getIsValid());
        assertEquals(1, user.getUserId());
        assertEquals("user", user.getClass_());
        assertEquals("abc321", user.getToken());
        // Dodatkowe weryfikacje czy poprawnie pobiera dane użytkownika.
    }

    @Test
    void testCheckLogin() {
        // Given
        Credentials credentials = new Credentials("john@example.com", "password");
        jdbcTemplate.execute("INSERT INTO \"user\" (userid, login, password) VALUES (1, 'john@example.com', 'password')");

        // When
        int userId = userRepository.checkLogin(credentials);

        // Then
        assertEquals(1, userId);
        // Dodatkowe weryfikacje czy poprawnie sprawdza dane logowania.
    }

    @Test
    void testAddUser() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        String login = "john@example.com";
        String password = "password";
        String class_ = "user";

        // When
        User user = userRepository.addUser(firstName, lastName, login, password, class_);

        // Then
        assertNotNull(user);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());
        assertEquals(class_, user.getClass_());
        assertNotNull(user.getToken());
        // Dodatkowe weryfikacje
    }

    @Test
    void testGetUserkey() {
        // Given
        jdbcTemplate.execute("INSERT INTO \"user\" (userid, userkey) VALUES (1, 'userkey123')");

        // When
        String userkey = userRepository.getUserkey(1);

        // Then
        assertNotNull(userkey);
        assertEquals("userkey123", userkey);
        // Dodatkowe weryfikacje
    }

    @Test
    void testUpdateUserkey() {
        // Given
        jdbcTemplate.execute("INSERT INTO \"user\" (userid, userkey) VALUES (1, 'oldUserkey')");

        // When
        userRepository.updateUserkey(1);

        // Then
        String newUserkey = jdbcTemplate.queryForObject("SELECT userkey FROM \"user\" WHERE userid = 1", String.class);
        assertNotNull(newUserkey);
        assertNotEquals("oldUserkey", newUserkey);
        // Dodatkowe weryfikacje np. czy poprawnie aktualizuje klucz użytkownika.
    }

    @Test
    void testExtendMembership() {
        // Given
        jdbcTemplate.execute("INSERT INTO \"user\" (userid) VALUES (1)");
        jdbcTemplate.execute("INSERT INTO membership (userid, expirationdate) VALUES (1, '2024-01-01')");

        // When
        userRepository.extendMembership(1);

        // Then
        LocalDate newExpirationDate = jdbcTemplate.queryForObject("SELECT expirationdate FROM membership WHERE userid = 1", LocalDate.class);
        assertNotNull(newExpirationDate);
        // Dodatkowe weryfikacje
    }

    // Dodaj pozostałe testy w podobny sposób

}
