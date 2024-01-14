package gym55.gym55.repository;

import gym55.gym55.UserkeyController;
import gym55.gym55.tableObjects.Credentials;
import gym55.gym55.tableObjects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Klasa obsługująca operacje na bazie danych związane z tabelą user
 */
@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * pobiera dane wybranego użytkownika z bazy danych
     * @param id id użytkownika
     * @return pobrany wiersz z bazy danych w postaci obiektu klasy User
     */
    public User getUser(int id){
        return jdbcTemplate.queryForObject(String.format("Select u.firstName, '2024-11-10' as isValid, u.userid, u.class_, 'abc321'  from \"user\" u " +
                "natural join membership m WHERE u.userid = %d", id), BeanPropertyRowMapper.newInstance(User.class));}

    public int checkLogin(Credentials credentials){
        return jdbcTemplate.queryForObject(String.format("SELECT u.userid FROM \"user\" u WHERE u.login = '%s' and u.password = '%s'", credentials.getEmail(), credentials.getPassword()),
                Integer.class);
    }


    /**
     * Dodaje nowego użytkownika do bazy danych
     * @param firstName imię użytkownika
     * @param lastName nazwisko użytkownika
     * @param login email użytkownika
     * @param class_ klasa dostępu użytkownika
     * @return dodany wiersz do tabeli w postaci obiektu klasy User
     * @throws RuntimeException gdy klasa obiektu nie nalezy do zbioru {'user', 'trainer', 'admin'}
     */
    public User addUser(String firstName, String lastName, String login, String password, String class_) throws RuntimeException{
        String userKey = UserkeyController.generateUserkey();
        if(!checkClassCorrectness(class_)){
            throw new RuntimeException("Invalid name of the class!");}
        jdbcTemplate.execute(String.format("Insert into \"user\" (firstname, lastname, login, password, class_, userkey)" +
                " values ('%s', '%s', '%s', '%s', '%s', '%s')", firstName, lastName, login, password, class_, userKey));
        addMembershipForNewUser();
        int id = getNewUserid();
        return getUser(id);
    }


    /**
     * pobiera z bazy danych klucz użytkownika
     * @param id id użytkownika
     * @return klucz użytkownika w postaci obiektu String
     */
    public String getUserkey(int id){
        return jdbcTemplate.queryForObject(String.format("Select u.userkey from \"user\" u where u.userid = %d",id), BeanPropertyRowMapper.newInstance(String.class));
    }

    /**
     * generuje nowy klucz dla użytkownika
     * @param id id użytkownika
     */
    public void updateUserkey(int id){
        String userkey = UserkeyController.generateUserkey();
        jdbcTemplate.execute(String.format("Update \"user\"" +
                "SET userkey = '%s'" +
                "WHERE userid = %d;",userkey,id));
    }

    public void extendMembership(int id){
        jdbcTemplate.execute(String.format("Update membership " +
                "SET expirationdate = (expirationdate + interval '1 month') " +
                "WHERE userid = %d;", id));
    }

    private Boolean checkClassCorrectness(String class_){
        String[] classes = {"user", "trainer", "admin"};
        List<String> classes_ = Arrays.asList(classes);
        return classes_.contains(class_);
    }

    private void addMembershipForNewUser(){
        int id = getNewUserid();
        jdbcTemplate.execute(String.format("Insert into membership (userid, isValid) values (%s,%s)",id,false));
    }



    private int getNewUserid(){
        return jdbcTemplate.queryForObject("Select max(userid) from \"user\"", Integer.class);
    }




}
