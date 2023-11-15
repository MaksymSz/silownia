package gym55.gym55.repository;

import gym55.gym55.UserkeyController;
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
        return jdbcTemplate.queryForObject(String.format("Select u.userid, u.firstName, u.lastName,u.email, u.class, m.isValid from \"user\" u " +
                "natural join membership m WHERE u.userid = %d", id), BeanPropertyRowMapper.newInstance(User.class));}

    /**
     * Dodaje nowego użytkownika do bazy danych
     * @param firstName imię użytkownika
     * @param lastName nazwisko użytkownika
     * @param email email użytkownika
     * @param class_ klasa dostępu użytkownika
     * @return dodany wiersz do tabeli w postaci obiektu klasy User
     * @throws RuntimeException gdy klasa obiektu nie nalezy do zbioru {'user', 'trainer', 'admin'}
     */
    public User addUser(String firstName, String lastName, String email, String class_) throws RuntimeException{
        String userKey = UserkeyController.generateUserkey();
        if(!checkClassCorrectness(class_)){
            throw new RuntimeException("Invalid name of the class!");}
        jdbcTemplate.execute(String.format("Insert into user (firstName, lastName, email, class, userkey" +
                "values (%s, %s, %s, %s, %s)", firstName, lastName, email, class_, userKey));
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


    private Boolean checkClassCorrectness(String class_){
        String[] classes = {"user", "trainer", "admin"};
        List<String> classes_ = Arrays.asList(classes);
        return classes_.contains(class_);
    }

    private void addMembershipForNewUser(){
        int id = getNewUserid();
        jdbcTemplate.execute(String.format("Insert into membership (userid, isValid) values (%s,%s)",id,'0'));
    }



    private int getNewUserid(){
        return jdbcTemplate.queryForObject("Select max(userid) from \"user\"", BeanPropertyRowMapper.newInstance(int.class));
    }



}
