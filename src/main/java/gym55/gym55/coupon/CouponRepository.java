package gym55.gym55.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Coupon getCoupon(int id){
        return jdbcTemplate.queryForObject(String.format("Select * from coupon where couponid = %d", id), BeanPropertyRowMapper.newInstance(Coupon.class));
    }

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

