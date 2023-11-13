package gym55.gym55;

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
     * @param id idkuponu w bazie danych
     * @return wiersz kuponu z bazy danych w postaci obiektu klasy Coupon
     */
    //TODO.txt dodać / zamienić wyszukiwanie Kuponu po "zawartości kuponu"??? bo id to 1, 2, 3.... a przecież my sprawdzamy po wartości kuponu. po "nazwie"? bo id to można strzelać i cały czas będzie.
    public Coupon getCoupon(int id){
        return jdbcTemplate.queryForObject(String.format("Select * from coupon where couponid = %d", id), BeanPropertyRowMapper.newInstance(Coupon.class));
    }

    /**
     * Funkcja dodająca kupon do bazy danych
     * @param name nazwa kuponu
     * @param discount wartość rabatu (wartości od 0 do 1)
     * @return dodany do bazy danych kupon w postaci obiektu klasy coupon
     * @throws RuntimeException jeśli rabat nie jest wartością między 0 a 1
     */
    public Coupon addCoupon(String name, float discount) throws RuntimeException{
        if(discount > 1 | discount < 0) {
            throw new RuntimeException("Discount has to be between 0 and 1");
        }
        jdbcTemplate.execute(String.format("insert into coupon(name,discount)" +
                "values (%s, %f)",name, discount));
        int id = jdbcTemplate.queryForObject("Select max(couponid) from coupon", BeanPropertyRowMapper.newInstance(int.class));
        return getCoupon(id);
        }
    }

