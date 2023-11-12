package gym55.gym55;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;

/**
 * A class which provides methods to handle database operations about users
 */
@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * Gets user from database
     * @param id the id of user
     * @return the User object
     */
    public User getUser(int id){
        return jdbcTemplate.queryForObject(String.format("Select u.userid, u.firstName, u.class, m.isValid from \"user\" u " +
                "natural join membership m WHERE u.userid = %d", id), BeanPropertyRowMapper.newInstance(User.class));}

    /**
     * Adds user to the database
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param email email of the user
     * @param class_ class of the user
     * @return the User object of the newly added User
     */
    public User addUser(String firstName, String lastName, String email, String class_){
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
     * Gets the userkey of specific user
     * @param id id of the user
     * @return a userkey
     */
    public String getUserkey(int id){
        return jdbcTemplate.queryForObject(String.format("Select u.userkey from \"user\" u where u.userid = %d",id), BeanPropertyRowMapper.newInstance(String.class));
    }

    /**
     * Updates the userkey of specific user
     * @param id of the user
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
