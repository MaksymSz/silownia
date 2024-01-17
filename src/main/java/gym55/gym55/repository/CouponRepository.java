package gym55.gym55.repository;

import gym55.gym55.tableObjects.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Klasa obsługująca operacje na bazie danych związane z tabelą coupon
 */
@Repository
public class CouponRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Funkcja pobierająca wybrany kupon z bazy danych
     *
     * @param id idkuponu w bazie danych
     * @return wiersz kuponu z bazy danych w postaci obiektu klasy Coupon
     */

    public Coupon getCoupon(int id) {
        return jdbcTemplate.queryForObject(String.format("Select * from coupon where couponid = %d", id), BeanPropertyRowMapper.newInstance(Coupon.class));
    }


    /**
     * Funkcja dodająca kupon do bazy danych
     *
     * @param name  nazwa kuponu
     * @param value wartość rabatu (wartości od 0 do 1)
     * @return dodany do bazy danych kupon w postaci obiektu klasy coupon
     */
    public Coupon addCoupon(String name, float value) {
        jdbcTemplate.execute(String.format("insert into coupon(name,discount)" +
                "values (%s, %f)", name, value));
        int id = jdbcTemplate.queryForObject("Select max(couponid) from coupon", BeanPropertyRowMapper.newInstance(Integer.class));
        return getCoupon(id);
    }


    public int checkCoupon(String name) {
        return jdbcTemplate.queryForObject(String.format("Select count(*) from coupon where name = '%s'", name), Integer.class);
    }
}
