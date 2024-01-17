package gym55.gym55.repository;

import gym55.gym55.repository.CouponRepository;
import gym55.gym55.tableObjects.Coupon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
class CouponRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void testGetCoupon() {
        // Given
        int couponId = 1;
        String couponName = "TestCoupon";
        float couponValue = 0.2f;

        jdbcTemplate.execute("INSERT INTO coupon (couponid, name, discount) VALUES (1, 'TestCoupon', 0.2)");

        // When
        Coupon coupon = couponRepository.getCoupon(couponId);

        // Then
        assertNotNull(coupon);
        assertEquals(couponId, coupon.getCouponId());
        assertEquals(couponName, coupon.getName());
        assertEquals(couponValue, coupon.getValue(), 0.01);
    }

    @Test
    void testAddCoupon() {
        // Given
        String couponName = "NewCoupon";
        float couponValue = 0.15f;

        // When
        Coupon addedCoupon = couponRepository.addCoupon(couponName, couponValue);

        // Then
        assertNotNull(addedCoupon);
        assertEquals(couponName, addedCoupon.getName());
        assertEquals(couponValue, addedCoupon.getValue(), 0.01);

        // Verify that the coupon is added to the database
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM coupon WHERE name = 'NewCoupon'", Integer.class);
        assertEquals(1, count);
    }

    @Test
    void testCheckCoupon() {
        // Given
        String existingCouponName = "ExistingCoupon";
        String nonExistingCouponName = "NonExistingCoupon";

        jdbcTemplate.execute("INSERT INTO coupon (couponid, name, discount) VALUES (1, 'ExistingCoupon', 0.2)");

        // When
        int existingCouponCount = couponRepository.checkCoupon(existingCouponName);
        int nonExistingCouponCount = couponRepository.checkCoupon(nonExistingCouponName);

        // Then
        assertEquals(1, existingCouponCount);
        assertEquals(0, nonExistingCouponCount);
    }


}